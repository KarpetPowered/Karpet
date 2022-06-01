package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeEffects

/**
 * Sparse Jungle biome
 * @author Interfiber
 */
class SparseJungle : MinecraftBiome() {
    init {
        this.biomeID = "sparse_jungle"
        this.effects = BiomeEffects.builder()
            .grassColor(this.stringToColor("#64C73F"))
            .foliageColor(this.stringToColor("#3EB80F"))
            .waterColor(this.stringToColor("#3F76E4"))
            .build()
    }
}
