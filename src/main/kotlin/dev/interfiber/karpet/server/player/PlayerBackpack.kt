package dev.interfiber.karpet.server.player

import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.ItemEntity
import net.minestom.server.entity.Player
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.block.Block
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

/**
 * Controls the players inventory, or backpack
 * @author Interfiber
 */
class PlayerBackpack {
    /**
     * Spawn a stack of items
     * @author Interfiber
     */
    fun spawnItemStack(Type: Material?, Position: Pos?, Container: InstanceContainer?) {
        val item = ItemEntity(ItemStack.of(Type!!))
        if (Position != null) {
            item.setInstance(Container!!, Position)
        }
    }
    /**
     * Called when a player picks up an item
     * @author Interfiber
     */

    fun pickupItemCallback(PickupEvent: PickupItemEvent) {
        val targetPlayer: Player = PickupEvent.entity as Player
        val itemType = PickupEvent.itemStack.material()
        targetPlayer.inventory.addItemStack(ItemStack.of(itemType, 1))
    }

    /**
     * Check if a player can mine a block
     * @author Interfiber
     */
    fun canMineBlock(blockType: Block, player: Player): Boolean {
        val itemInHand = player.itemInMainHand
        return !(blockType == Block.IRON_ORE && itemInHand == ItemStack.of(Material.WOODEN_PICKAXE))
    }
}