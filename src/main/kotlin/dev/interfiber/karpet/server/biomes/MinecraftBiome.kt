package dev.interfiber.karpet.server.biomes

import net.minestom.server.utils.NamespaceID
import net.minestom.server.world.biomes.Biome
import net.minestom.server.world.biomes.BiomeEffects

/**
 * Represents a Minecraft biome
 * @author Interfiber
 */
open class MinecraftBiome {
    var effects: BiomeEffects? = null
//    var biome: Biome? = null
    var biomeID: String? = null
    /**
     * Convert color code to int
     * @return Int
     * @author Interfiber
     */
    fun stringToColor(color: String): Int {
        return Integer.parseInt(color.replaceFirst("#", ""), 16)
    }
    /**
     * Return the MinecraftBiome as a minestom biome
     * @return Biome
     * @author Interfiber
     */

    fun convertToBiome(): Biome? {
        return Biome.builder()
            .effects(this.effects)
            .name(biomeID?.let { NamespaceID.from(it) })
            .build()
    }
}
