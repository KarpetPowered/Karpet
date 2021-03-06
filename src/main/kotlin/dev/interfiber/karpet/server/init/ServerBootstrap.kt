package dev.interfiber.karpet.server.init

import dev.interfiber.karpet.server.WorldSaveThread
import dev.interfiber.karpet.server.biomes.BiomeLoader
import dev.interfiber.karpet.server.commands.GiveItemCommand
import dev.interfiber.karpet.server.commands.GivePermissonCommand
import dev.interfiber.karpet.server.commands.SaveWorldCommand
import dev.interfiber.karpet.server.config.ConfigLoader
import dev.interfiber.karpet.server.config.ConfigUtils
import dev.interfiber.karpet.server.events.PlayerLeave
import dev.interfiber.karpet.server.events.PlayerLogin
import dev.interfiber.karpet.server.metrics.Tracker
import dev.interfiber.karpet.server.recipes.RecipeLoader
import dev.interfiber.karpet.server.smelting.SmeltingRecipeLoader
import mu.KotlinLogging
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.event.server.ServerListPingEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.timer.Scheduler
import net.minestom.server.timer.TaskSchedule
import java.io.File
import java.io.FileWriter

private val logger = KotlinLogging.logger {}

/**
 * Server bootstrapped
 * @author Interfiber
 */
class ServerBootstrap {
    /**
     * Starts the karpet server
     * Registers biomes, commands, recipes, and loads the world
     */
    fun startServer() {
        // Check for config file
        logger.info("Checking for config file...")
        val configData: String = if (!File("karpet.toml").exists()) {
            logger.info("Creating server UUID...")
            val writer = FileWriter(".karpetuuid")
            writer.write(ConfigUtils().generateServerUUID())
            writer.close()

            logger.info("Failed to find config file, creating it")
            ConfigUtils().createConfig()
        } else {
            logger.info("Found config file")
            ConfigUtils().readConfig()
        }

        // Load the config file
        logger.info("Loading config file...")
        val config = configData.let { ConfigLoader().getConfig(it) }
        val maxPlayers = config?.getTable("server")?.getLong("max-players")?.toInt()

        // Create server
        logger.info("Initializing MinecraftServer...")
        val minecraftServer = MinecraftServer.init()
        MinecraftServer.setBrandName("Karpet")
        val onlineModeEnable = config?.getTable("server")?.getBoolean("online-mode")
        if (onlineModeEnable == true) {
            logger.info("Enabling player authentication...")
            MojangAuth.init()
        } else {
            logger.warn("Player authentication is disabled, this can allow players to play")
            logger.warn("on the server without an account, or force their username to anything")
            logger.warn("set 'online-mode = true' in karpet.toml to prevent this warning!")
        }

        // Load recipes
        logger.info("Registering recipes...")
        RecipeLoader.loadAllRecipes()

        // Load smelting recipes
        logger.info("Registering smelting recipes...")
        SmeltingRecipeLoader.loadAllRecipes()

        // Biomes
        logger.info("Registering biomes...")
        BiomeLoader.loadBiomes(MinecraftServer.getBiomeManager())

        // Load world
        logger.info("Preparing world...")

        val instanceManager = MinecraftServer.getInstanceManager()
        val instanceContainer = instanceManager.createInstanceContainer()
        instanceContainer.chunkLoader = AnvilLoader("world")
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()

        // Metrics
        val serverConfig = config?.getTable("server")
        if (serverConfig?.getBoolean("enable-bstats") == true) {
            logger.info("Enabling metrics...")
            Tracker().reportInfo(serverConfig)
        }

        // Commands
        logger.info("Registering commands...")
        MinecraftServer.getCommandManager().register(SaveWorldCommand(instanceContainer))
        MinecraftServer.getCommandManager().register(GivePermissonCommand())
        MinecraftServer.getCommandManager().register(GiveItemCommand())

        // Add global events
        logger.info("Adding server events...")
        globalEventHandler.addListener(
            PlayerLoginEvent::class.java
        ) { event: PlayerLoginEvent? ->
            if (event != null) {
                // check max players
                if (MinecraftServer.getConnectionManager().onlinePlayers.size > maxPlayers!!) {
                    logger.warn("Kicking player " + event.player.username + ", due to player limit")
                    event.player.kick("Server is full, please connect at a later time")
                } else {
                    PlayerLogin().fireEvent(event, instanceContainer)
                }
            }
        }
        globalEventHandler.addListener(
            ServerListPingEvent::class.java
        ) { pingEvent: ServerListPingEvent? ->
            if (pingEvent != null) {
                ServerListView().serverPingHandler(pingEvent, config)
            }
        }
        globalEventHandler.addListener(
            PlayerDisconnectEvent::class.java
        ) { leaveEvent: PlayerDisconnectEvent? ->
            if (leaveEvent != null) {
                PlayerLeave().fireEvent(leaveEvent.player)
            }
        }
        val scheduler: Scheduler = MinecraftServer.getSchedulerManager()
        scheduler.submitTask {
            logger.info("Running automatic world save...")
            val worldSaveThread = WorldSaveThread(instanceContainer)
            val thread = Thread(worldSaveThread)
            thread.start()
            TaskSchedule.minutes(2)
        }
        MinecraftServer.getSchedulerManager().buildShutdownTask(ServerShutdown(instanceContainer))

        // Start server
        logger.info("Starting server...")
        minecraftServer.start("0.0.0.0", 25565)
    }
}
