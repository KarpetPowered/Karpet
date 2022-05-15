package dev.interfiber.karpet.server.utils

import net.minestom.server.instance.InstanceContainer
import net.minestom.server.tag.Tag
import org.jglrxavpok.hephaistos.nbt.NBTCompound


class SpawnLocator {
    fun getSpawnLocation(instance: InstanceContainer): SpawnLocation? {
        val compound = instance.getTag(Tag.NBT("Data")) as NBTCompound
        val spawnX = compound.getInt("SpawnX")
        val spawnY = compound.getInt("SpawnY")
        val spawnZ = compound.getInt("SpawnZ")
        val location = SpawnLocation()
        if (spawnX != null && spawnY != null && spawnZ != null) {
            location.spawnX = spawnX.toDouble()
            location.spawnY = spawnY.toDouble()
            location.spawnZ = spawnZ.toDouble()
        } else {
            println("Util error: Failed to read NBT data!")
            location.spawnX = 0.0
            location.spawnY = 200.0
            location.spawnZ = 0.0
        }
        return location
    }
}