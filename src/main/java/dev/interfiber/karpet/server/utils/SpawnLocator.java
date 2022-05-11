package dev.interfiber.karpet.server.utils;

import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.tag.Tag;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

/**
 *
 * @author persephone
 */
public class SpawnLocator {
    
    public static SpawnLocation GetSpawnLocation(InstanceContainer instance) {
            NBTCompound Compound = (NBTCompound) instance.getTag(Tag.NBT("Data"));
            Integer SpawnX = Compound.getInt("SpawnX");
            Integer SpawnY = Compound.getInt("SpawnY");
            Integer SpawnZ = Compound.getInt("SpawnZ");
            SpawnLocation Location = new SpawnLocation();
            if (SpawnX != null && SpawnY != null && SpawnZ != null) {
                Location.SpawnX = SpawnX;
                Location.SpawnY = SpawnY;
                Location.SpawnZ = SpawnZ;
            } else {
                System.out.println("Util error: Failed to read NBT data!");
                Location.SpawnX = 0;
                Location.SpawnY = 200;
                Location.SpawnZ = 0;
            }
            return Location;
    }
}
