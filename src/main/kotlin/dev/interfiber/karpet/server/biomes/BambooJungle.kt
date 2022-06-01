package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeEffects

/**
 * Bamboo Jungle biome
 * @author Interfiber
 */
class BambooJungle : MinecraftBiome() {
    init {
        this.biomeID = "bamboo_jungle"
        this.effects = BiomeEffects.builder()
            .grassColor(this.stringToColor("#59C93C"))
            .foliageColor(this.stringToColor("#30BB0B"))
            .waterColor(this.stringToColor("#3F76E4"))
            .build();
    }
}