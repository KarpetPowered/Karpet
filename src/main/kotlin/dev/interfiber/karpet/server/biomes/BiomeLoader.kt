package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeManager

/**
 * Registers all biomes on server startup
 * @author Interfiber
 */
object BiomeLoader {
    /**
     * Load all biomes into memory
     * @param manager BiomeManager to register biomes for
     * @author Interfiber
     */
    fun loadBiomes(manager: BiomeManager) {
        val swampBiome = SwampBiome()
        val jungleBiome = JungleBiome()
        val sparseJungle = SparseJungle()
        val bambooJungle = BambooJungle()
        val jungleHills = JungleHills()

        manager.addBiome(swampBiome.convertToBiome())
        manager.addBiome(jungleBiome.convertToBiome())
        manager.addBiome(sparseJungle.convertToBiome())
        manager.addBiome(bambooJungle.convertToBiome())
        manager.addBiome(jungleHills.convertToBiome())
    }
}
