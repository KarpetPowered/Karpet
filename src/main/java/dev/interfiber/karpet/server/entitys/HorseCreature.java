package dev.interfiber.karpet.server.entitys;

/**
 *
 * @author persephone
 */

import net.minestom.server.entity.ai.EntityAIGroupBuilder;
import net.minestom.server.entity.ai.goal.RandomLookAroundGoal;
import net.minestom.server.entity.ai.goal.RandomStrollGoal;
import net.minestom.server.entity.EntityCreature;
import net.minestom.server.entity.EntityType;

public class HorseCreature extends EntityCreature {

    public HorseCreature() {
        super(EntityType.HORSE);
        addAIGroup(
                new EntityAIGroupBuilder()
                        .addGoalSelector(new RandomLookAroundGoal(this, 20))
                        .addGoalSelector(new RandomStrollGoal(this, 9))
                        .build()
        );
    }
}
