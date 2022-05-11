package dev.interfiber.karpet.server.utils;

import dev.interfiber.karpet.KarpetLauncher;
import dev.interfiber.karpet.server.entitys.EntityStats;
import dev.interfiber.karpet.server.entitys.ZombieCreature;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.instance.InstanceContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author persephone
 */
public class Spawner {
    public EntityStats Stats;
    private static final Logger LOG = LogManager.getLogger(KarpetLauncher.class);
    public Spawner(){
        
    }
    
    public void SpawnEntity(EntityType Type, Pos Position, InstanceContainer SpawnInstance){
        Entity SpawnEntity = null;
        if (Type == EntityType.ZOMBIE) {
            SpawnEntity = new ZombieCreature(this.Stats);
            
        }
        if (SpawnEntity == null){
           LOG.error("Unsupported entity type!");
           return;
        }
        SpawnEntity.setInstance(SpawnInstance, Position);
    }
}
