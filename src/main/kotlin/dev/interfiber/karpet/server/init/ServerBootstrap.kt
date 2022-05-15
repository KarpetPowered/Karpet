package dev.interfiber.karpet.server.init
import dev.interfiber.karpet.server.events.PlayerLogin
import dev.interfiber.karpet.server.recipes.RecipeLoader
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.AnvilLoader

class ServerBootstrap {
    fun startServer(){
        val minecraftServer = MinecraftServer.init()
        RecipeLoader.loadAllRecipes()
        val instanceManager = MinecraftServer.getInstanceManager()
        val instanceContainer = instanceManager.createInstanceContainer()
        instanceContainer.chunkLoader = AnvilLoader("world")
        val globalEventHandler = MinecraftServer.getGlobalEventHandler()
        globalEventHandler.addListener(
            PlayerLoginEvent::class.java
        ) { event: PlayerLoginEvent? ->
            if (event != null) {
                PlayerLogin().fireEvent(event, instanceContainer)
            }
        }
        minecraftServer.start("0.0.0.0", 25565)
    }
}