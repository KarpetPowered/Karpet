package dev.interfiber.karpet.server.biomes

import net.minestom.server.world.biomes.BiomeEffects
import org.jglrxavpok.hephaistos.mcdata.Biome
import java.awt.Color

class JungleHills : MinecraftBiome() {
    init {
        this.biomeID = "jungle_hills"
        this.effects = BiomeEffects.builder()
            .grassColor(this.stringToColor("#64C73F"))
            .foliageColor(this.stringToColor("#3EB80F"))
            .waterColor(this.stringToColor("#3F76E4"))
            .build();
    }
}