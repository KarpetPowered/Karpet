package dev.interfiber.karpet.server.player

import dev.interfiber.karpet.server.utils.Probability
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.block.Block
import net.minestom.server.item.Material

/**
 * Handles block breaking for a player
 * @author Interfiber
 */
object PlayerBlockHandler {
    /**
     * Called when a player breaks a block
     * @author Interfiber
     */
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
        } else if (blockType === Block.STONE){
            // TODO Once enchantments are done, check if we have silk touch
            PlayerBackpack().spawnItemStack(Material.COBBLESTONE, eventPlayer.position, playerInstance)
        } else if (blockType == Block.IRON_ORE){
            if (PlayerBackpack().canMineBlock(Block.IRON_ORE, eventPlayer)){
                PlayerBackpack().spawnItemStack(Material.RAW_IRON, eventPlayer.position, playerInstance)
            }
        } else if (blockType == Block.JUNGLE_LOG) {
            PlayerBackpack().spawnItemStack(Material.JUNGLE_LOG, eventPlayer.position, playerInstance)
        } else if (blockType == Block.SPRUCE_LOG){
            PlayerBackpack().spawnItemStack(Material.SPRUCE_LOG, eventPlayer.position, playerInstance)
        } else if (blockType == Block.BIRCH_LOG){
            PlayerBackpack().spawnItemStack(Material.BIRCH_LOG, eventPlayer.position, playerInstance)
        } else if (blockType == Block.COAL_ORE){
            PlayerBackpack().spawnItemStack(Material.COAL, eventPlayer.position, playerInstance)
        }
        eventPlayer.inventory.update()
    }
}