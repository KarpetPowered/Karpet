package dev.interfiber.karpet.server.init
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
import net.minestom.server.instance.AnvilLoader
import java.io.File

private val logger = KotlinLogging.logger {}

class ServerBootstrap {
    fun startServer(){
        // Check for config file
        logger.info("Checking for config file...")
        var configData: String? = if (!File("karpet.toml").exists()){
            logger.info("Failed to find config file, creating it")
            ConfigUtils().createConfig()
        } else {
            logger.info("Found config file")
            ConfigUtils().readConfig()
        }

        // Load the config file
        logger.info("Loading config file...")
        val config = configData?.let { ConfigLoader().getConfig(it) }

        // Load recipes
        logger.info("Loading recipes...")
        val minecraftServer = MinecraftServer.init()
        RecipeLoader.loadAllRecipes()

        // Load world
        logger.info("Preparing world...")
        val instanceManager = MinecraftServer.getInstanceManager()
        val instanceContainer = instanceManager.createInstanceContainer()
        instanceContainer.chunkLoader = AnvilLoader("world")
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()

        // Add global events
        logger.info("Adding server events...")
        globalEventHandler.addListener(
            PlayerLoginEvent::class.java
        ) { event: PlayerLoginEvent? ->
            if (event != null) {
                PlayerLogin().fireEvent(event, instanceContainer)
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