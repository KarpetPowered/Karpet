package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeEffects

/**
 * Jungle Biome
 * @author Interfiber
 */
class JungleBiome : MinecraftBiome() {
    init {
        this.biomeID = "jungle"
        this.effects = BiomeEffects.builder()
            .grassColor(this.stringToColor("#59C93C"))
            .foliageColor(this.stringToColor("#30BB0B"))
            .waterColor(this.stringToColor("#3F76E4"))
            .build();
    }
}