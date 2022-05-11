package dev.interfiber.karpet.server.entitys;

import net.minestom.server.attribute.Attribute;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;

/**
 *
 * @author persephone
 */

public class MinecraftEntity extends EntityCreature {
    public MinecraftEntity(EntityType entityType, EntityStats stats){
        super(entityType);
        getAttribute(Attribute.MAX_HEALTH).setBaseValue(stats.MaxHealth);
        getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(stats.AttackDamage);
    }
}
