package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeManager

object BiomeLoader {

    fun loadBiomes(manager: BiomeManager){
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