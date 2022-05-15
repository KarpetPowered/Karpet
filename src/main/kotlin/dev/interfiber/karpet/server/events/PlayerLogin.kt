package dev.interfiber.karpet.server.events

import dev.interfiber.karpet.server.entitys.EntityStats
import dev.interfiber.karpet.server.player.PlayerBackpack
import dev.interfiber.karpet.server.player.PlayerBlockHandler
import dev.interfiber.karpet.server.player.PlayerCraftingHandler
import dev.interfiber.karpet.server.recipes.RecipeLoader
import dev.interfiber.karpet.server.utils.SpawnLocation
import dev.interfiber.karpet.server.utils.SpawnLocator
import dev.interfiber.karpet.server.utils.Spawner
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.event.entity.EntityDamageEvent
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.InstanceContainer


class PlayerLogin {
    fun fireEvent(event: PlayerLoginEvent, instanceContainer: InstanceContainer?) {
        val player: Player = event.player
        if (instanceContainer != null) {
            event.setSpawningInstance(instanceContainer)
        }
        // Get spawn location
        val location: SpawnLocation? = instanceContainer?.let { SpawnLocator().getSpawnLocation(it) }
        if (location != null) {
            player.respawnPoint = Pos(location.spawnX, location.spawnY, location.spawnZ)
        }
        player.eventNode().addListener(
            PickupItemEvent::class.java
        ) { pickupItemEvent: PickupItemEvent? ->
            if (pickupItemEvent != null) {
                PlayerBackpack().pickupItemCallback(pickupItemEvent)
            }
        }
        player.eventNode().addListener(PlayerBlockBreakEvent::class.java, PlayerBlockHandler::breakBlockHandler)
        PlayerCraftingHandler().addCraftEvent(player, RecipeLoader.getLoadedRecipes())
        Audiences.all().sendMessage(
            Component.text(
                player.username + " has joined the game",
                NamedTextColor.GREEN
            )
        )
//        val stats = EntityStats()
//        stats.attackDamage = 5
//        stats.maxHealth = 50
//        val spawner = Spawner()
//        spawner.stats = stats
//        spawner.spawnEntity(EntityType.ZOMBIE, player.respawnPoint, instanceContainer)
    }
}