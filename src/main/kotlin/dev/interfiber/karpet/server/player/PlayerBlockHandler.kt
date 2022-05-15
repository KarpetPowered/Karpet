package dev.interfiber.karpet.server.player

import dev.interfiber.karpet.server.utils.Probability
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.block.Block
import net.minestom.server.item.Material

/**
 *
 * @author persephone
 */
object PlayerBlockHandler {
    fun breakBlockHandler(BreakBlockEvent: PlayerBlockBreakEvent) {
        val eventPlayer = BreakBlockEvent.player
        val playerInstance = eventPlayer.instance as InstanceContainer?
        val blockType = BreakBlockEvent.block
        if (blockType === Block.GRASS_BLOCK) {
            PlayerBackpack().spawnItemStack(Material.DIRT, eventPlayer.position, playerInstance)
        } else if (blockType === Block.OAK_LOG) {
            PlayerBackpack().spawnItemStack(Material.OAK_LOG, eventPlayer.position, playerInstance)
        } else if (blockType === Block.OAK_PLANKS) {
            PlayerBackpack().spawnItemStack(Material.OAK_PLANKS, eventPlayer.position, playerInstance)
        } else if (blockType === Block.CRAFTING_TABLE) {
            PlayerBackpack().spawnItemStack(Material.CRAFTING_TABLE, eventPlayer.position, playerInstance)
        } else if (blockType === Block.GRASS || blockType === Block.TALL_GRASS) {
            val shouldBreak: Boolean = Probability.calculate(12.5)
            if (shouldBreak) {
                PlayerBackpack().spawnItemStack(Material.WHEAT_SEEDS, eventPlayer.position, playerInstance)
            }
        }
        eventPlayer.inventory.update()
    }
}