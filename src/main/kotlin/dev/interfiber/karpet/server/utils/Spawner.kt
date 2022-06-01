package dev.interfiber.karpet.server.utils

import dev.interfiber.karpet.server.entitys.EntityStats
import dev.interfiber.karpet.server.entitys.ZombieCreature
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType
import net.minestom.server.instance.InstanceContainer

/**
 * Entity spawner(not the block)
 * @author Interfiber
 */
class Spawner {
    var stats: EntityStats? = null
    /**
     * Spawn an entity in at a position
     * @param Type Type of the entity to spawn
     * @param Position Position in 3D space to spawn the entity at
     * @param SpawnInstance Instance to spawn the entity in
     * @author Interfiber
     */
    fun spawnEntity(Type: EntityType, Position: Pos?, SpawnInstance: InstanceContainer?) {
        var entityToSpawn: Entity? = null
        if (Type === EntityType.ZOMBIE) {
            entityToSpawn = ZombieCreature(stats!!)
        }
        if (entityToSpawn == null) {
            println("Invalid entity type")
            return
        }
        println(Position)
        if (Position != null && SpawnInstance != null) {
            entityToSpawn.setInstance(SpawnInstance, Position)
        } else {
            println("Position: $Position")
            println("SpawnInstance: $SpawnInstance")
            println("One of the above variables is null!")
            println("Failed to spawn entity")
            return
        }
    }
}
