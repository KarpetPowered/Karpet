package dev.interfiber.karpet.server.init

import mu.KotlinLogging
import net.minestom.server.instance.InstanceContainer

private val logger = KotlinLogging.logger {}


class ServerShutdown(container: InstanceContainer) : Runnable {
    private val instance: InstanceContainer

    init {
        this.instance = container
    }
    override fun run() {
        logger.info("Running instance world save...")
        instance.saveChunksToStorage()
        logger.info("Shutting down server...")
    }

}