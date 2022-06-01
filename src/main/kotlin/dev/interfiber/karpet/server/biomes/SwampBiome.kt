package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeEffects

/**
 * Swamp biome
 * @author Interfiber
 */
class SwampBiome : MinecraftBiome() {
    init {
        this.biomeID = "swamp"
        this.effects = BiomeEffects.builder()
            .grassColor(this.stringToColor("#6A7039"))
            .build()
    }
}