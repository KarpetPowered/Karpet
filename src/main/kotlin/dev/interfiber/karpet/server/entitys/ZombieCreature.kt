package dev.interfiber.karpet.server.entitys;

import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.entity.ai.EntityAIGroupBuilder
import net.minestom.server.entity.ai.goal.MeleeAttackGoal
import net.minestom.server.entity.ai.goal.RandomLookAroundGoal
import net.minestom.server.entity.ai.target.ClosestEntityTarget
import net.minestom.server.entity.damage.DamageType
import net.minestom.server.event.entity.EntityAttackEvent
import net.minestom.server.utils.time.TimeUnit

/**
 * Zombie entity
 * @author Interfiber
 */
class ZombieCreature(stats: EntityStats) : MinecraftEntity(EntityType.ZOMBIE, stats){
    init {
        addAIGroup(
           listOf(MeleeAttackGoal(this, 1.2, 20, TimeUnit.SERVER_TICK)),
           listOf(ClosestEntityTarget(this, 32F, Player::class.java))
        )
        addAIGroup(
            EntityAIGroupBuilder()
                .addGoalSelector(RandomLookAroundGoal(this, 15))
                .build()
        );
        eventNode().addListener(EntityAttackEvent::class.java) { attackEvent ->
            (attackEvent.target as Player?)?.damage(
                DamageType.fromEntity(attackEvent.entity),
                stats.attackDamage.toFloat()
            );
        }
    }
}