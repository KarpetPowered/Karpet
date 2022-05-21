package dev.interfiber.karpet.server.init

import dev.interfiber.karpet.server.metrics.Tracker
import dev.interfiber.karpet.server.config.ConfigLoader
import dev.interfiber.karpet.server.config.ConfigUtils
import dev.interfiber.karpet.server.events.PlayerLeave
import dev.interfiber.karpet.server.events.PlayerLogin
import dev.interfiber.karpet.server.recipes.RecipeLoader
import mu.KotlinLogging
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.event.server.ServerListPingEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.instance.AnvilLoader
import java.io.File
import java.io.FileWriter


private val logger = KotlinLogging.logger {}

class ServerBootstrap {
    fun startServer(){
        // Check for config file
        logger.info("Checking for config file...")
        var configData: String? = if (!File("karpet.toml").exists()){
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
        val config = configData?.let { ConfigLoader().getConfig(it) }
        val maxPlayers = config?.getTable("server")?.getLong("max-players")?.toInt()

        // Create server
        logger.info("Initializing MinecraftServer...")
        val minecraftServer = MinecraftServer.init()
        val onlineModeEnable = config?.getTable("server")?.getBoolean("online-mode")
        if (onlineModeEnable == true){
            logger.info("Enabling player authentication...")
            MojangAuth.init();
        } else {
            logger.warn("Player authentication is disabled, this can allow players to play")
            logger.warn("on the server without an account, or force their username to anything")
            logger.warn("set 'online-mode = true' in karpet.toml to prevent this warning!")
        }

        // Load recipes
        logger.info("Loading recipes...")
        RecipeLoader.loadAllRecipes()

        // Load world
        logger.info("Preparing world...")

        val instanceManager = MinecraftServer.getInstanceManager()
        val instanceContainer = instanceManager.createInstanceContainer()
        instanceContainer.chunkLoader = AnvilLoader("world")
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()


        // Metrics
        val serverConfig = config?.getTable("server")
        if (serverConfig?.getBoolean("enable-bstats") == true){
            logger.info("Enabling metrics...")
            Tracker().reportInfo(serverConfig)
        }

        // Add global events
        logger.info("Adding server events...")
        globalEventHandler.addListener(
            PlayerLoginEvent::class.java
        ) { event: PlayerLoginEvent? ->
            if (event != null) {
                // check max players
                if (MinecraftServer.getConnectionManager().onlinePlayers.size > maxPlayers!!){
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

        // Start server
        logger.info("Starting server...")
        minecraftServer.start("0.0.0.0", 25565)
    }
}