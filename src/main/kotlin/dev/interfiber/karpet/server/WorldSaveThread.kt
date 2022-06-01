package dev.interfiber.karpet.server

import net.minestom.server.instance.InstanceContainer

/**
 * Saves the world on another thread
 * @author Interfiber
 */
class WorldSaveThread(container: InstanceContainer) : Runnable {
    private val instance: InstanceContainer
    init {
        this.instance = container
    }

    override fun run() {
        this.instance.saveChunksToStorage()
    }
}
