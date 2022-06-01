package dev.interfiber.karpet.server.entitys

import net.minestom.server.attribute.Attribute
import net.minestom.server.entity.EntityCreature
import net.minestom.server.entity.EntityType

/**
 * Represents a entity in minecraft
 * @author Interfiber
 */
open class MinecraftEntity(entityType: EntityType?, stats: EntityStats) : EntityCreature(entityType!!) {
    init {
        getAttribute(Attribute.MAX_HEALTH).baseValue = stats.maxHealth.toFloat()
        getAttribute(Attribute.ATTACK_DAMAGE).baseValue = stats.attackDamage.toFloat()
    }
}