package dev.interfiber.karpet.server.init

import mu.KotlinLogging
import net.kyori.adventure.text.Component
import net.minestom.server.instance.InstanceContainer

private val logger = KotlinLogging.logger {}


/**
 * Server shutdown handler
 * Saves servers world & shutsdown the server
 */
class ServerShutdown(container: InstanceContainer) : Runnable {
    private val instance: InstanceContainer

    init {
        this.instance = container
    }

    override fun run() {
        instance.sendMessage(Component.text("[KARPET] Running final world save, server will shutdown once complete"))
        logger.info("Running instance world save...")
        instance.saveChunksToStorage()
        instance.sendMessage(Component.text("[KARPET] Final world save complete, allow server to shutdown"))
        logger.info("Shutting down server...")
    }

}