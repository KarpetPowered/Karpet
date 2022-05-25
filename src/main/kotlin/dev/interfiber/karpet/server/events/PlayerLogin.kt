package dev.interfiber.karpet.server.events

import dev.interfiber.karpet.server.player.*
import dev.interfiber.karpet.server.recipes.RecipeLoader
import dev.interfiber.karpet.server.smelting.SmeltingRecipeLoader
import dev.interfiber.karpet.server.utils.SpawnLocation
import dev.interfiber.karpet.server.utils.SpawnLocator
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.block.Block
import net.minestom.server.item.Material


class PlayerLogin {
    fun fireEvent(event: PlayerLoginEvent, instanceContainer: InstanceContainer) {
        val player: Player = event.player
        event.setSpawningInstance(instanceContainer)
        // Get spawn location
        val location: SpawnLocation? = SpawnLocator().getSpawnLocation(instanceContainer)
        if (location != null) {
            player.respawnPoint = Pos(location.spawnX, location.spawnY, location.spawnZ)
        }
        player.eventNode().addListener(PlayerBlockBreakEvent::class.java, PlayerBlockHandler::breakBlockHandler)
        PlayerCraftingHandler().addCraftEvent(player, RecipeLoader.getLoadedRecipes())
        player.eventNode().addListener(
            PlayerBlockInteractEvent::class.java
        ) { interactEvent: PlayerBlockInteractEvent? ->
            val blockType = interactEvent?.block
            if (blockType == Block.CRAFTING_TABLE){
                PlayerCraftingTableHandler().addCraftingTableHandler(player, RecipeLoader.getLoadedRecipes())
            } else if (blockType == Block.FURNACE){
                PlayerSmeltingHandler().addFurnaceEvent(event.player, SmeltingRecipeLoader.getLoadedRecipes())
            }
        }
        player.eventNode().addListener(
            PickupItemEvent::class.java
        ) { pickupItemEvent: PickupItemEvent? ->
            if (pickupItemEvent != null) {
                PlayerBackpack().pickupItemCallback(pickupItemEvent)
            }
        }
        Audiences.all().sendMessage(
            Component.text(
                player.username + " joined the game",
                NamedTextColor.YELLOW
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