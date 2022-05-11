package dev.interfiber.karpet.server.entitys;

import java.util.List;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.ai.EntityAIGroupBuilder;
import net.minestom.server.entity.ai.goal.MeleeAttackGoal;
import net.minestom.server.entity.ai.goal.RandomLookAroundGoal;
import net.minestom.server.entity.ai.target.ClosestEntityTarget;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.event.entity.EntityAttackEvent;
import net.minestom.server.utils.time.TimeUnit;

/**
 *
 * @author persephone
 */


public class ZombieCreature extends MinecraftEntity {
    public ZombieCreature(EntityStats Stats) {
        super(EntityType.ZOMBIE, Stats);
        addAIGroup(
           List.of(new MeleeAttackGoal(this, 1.2, 20, TimeUnit.SERVER_TICK)),
           List.of(new ClosestEntityTarget(this, 32, Player.class))
        );
        addAIGroup(
            new EntityAIGroupBuilder()
                .addGoalSelector(new RandomLookAroundGoal(this, 15))
                .build()
        );
        eventNode().addListener(EntityAttackEvent.class, event -> {
            if (event.getTarget() instanceof Player player){
                player.damage(DamageType.fromEntity(event.getEntity()), 5);
            }
        });
    }
}
