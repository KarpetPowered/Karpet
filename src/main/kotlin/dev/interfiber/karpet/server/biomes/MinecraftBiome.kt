package dev.interfiber.karpet.server.biomes

import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.biomes.Biome
import net.minestom.server.world.biomes.BiomeEffects

open class MinecraftBiome {
    var effects: BiomeEffects? = null
//    var biome: Biome? = null
    var biomeID: String? = null

    fun stringToColor(color: String) : Int {
        return Integer.parseInt(color.replaceFirst("#", ""), 16)
    }

    fun convertToBiome(): Biome? {
        return Biome.builder()
            .effects(this.effects)
            .name(biomeID?.let { NamespaceID.from(it) })
            .build();
    }
}