package dev.interfiber.karpet.server.player

import dev.interfiber.karpet.server.recipes.InventoryConstants
import dev.interfiber.karpet.server.smelting.MinecraftSmeltingRecipe
import mu.KotlinLogging
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.concurrent.atomic.AtomicReference

private val logger = KotlinLogging.logger {}

/**
 * Handle player smelting with a furnace
 * @author Interfiber
 */
class PlayerSmeltingHandler {

    /**
     * Fill an entire HashMap with air
     * @author Interfiber
     */
    private fun initAir(selectedItems: HashMap<Int, ItemStack>): HashMap<Int, ItemStack> {
        selectedItems[InventoryConstants.SmeltingFuelSlot] = ItemStack.AIR
        selectedItems[InventoryConstants.SmeltingInputSlot] = ItemStack.AIR
        selectedItems[InventoryConstants.SmeltingOutputSlot] = ItemStack.AIR
        return selectedItems
    }
    /**
     * Player a sound to the given player
     * @author Interfiber
     */
    private fun playSound(player: Player, sound: String?, source: String?, volume: Float, pitch: Float) {
        player.playSound(Sound.sound(Key.key(sound!!), Sound.Source.valueOf(source!!), volume, pitch))
    }
    /**
     * Check if an item is a valid fuel
     * @author Interfiber
     */
    private fun fuelAccept(fuel: ItemStack): Boolean {
        val type = fuel.material()
        return if (type == Material.COAL){
            true
        } else if (type == Material.OAK_LOG) {
            true
        } else {
            false
        }
    }
    /**
     * Called when a player opens the furnace
     * @author Interfiber
     */
    fun addFurnaceEvent(player: Player, recipes: List<MinecraftSmeltingRecipe>){
        val smeltingInventory = Inventory(InventoryType.FURNACE, "Smelting")
        var selectedItems = HashMap<Int, ItemStack>()
        val recipeOutputID = AtomicReference("")
        selectedItems = initAir(selectedItems)
        smeltingInventory.addInventoryCondition { targetPlayer, slot, _, inventoryConditionResult ->
            if (inventoryConditionResult.cursorItem != ItemStack.AIR){
                selectedItems[slot] = inventoryConditionResult.cursorItem
            } else {
                selectedItems[slot] = ItemStack.AIR
            }

            if (slot == InventoryConstants.SmeltingOutputSlot && recipeOutputID.get() != null){
                logger.info(targetPlayer.username + " smelted a " + recipeOutputID)
                recipeOutputID.set(null)
                // Get values

                val fuelAmount = smeltingInventory.getItemStack(InventoryConstants.SmeltingFuelSlot).amount()
                val fuelType = smeltingInventory.getItemStack(InventoryConstants.SmeltingFuelSlot).material()

                val inputAmount = smeltingInventory.getItemStack(InventoryConstants.SmeltingInputSlot).amount()
                val inputType = smeltingInventory.getItemStack(InventoryConstants.SmeltingInputSlot).material()

                smeltingInventory.setItemStack(InventoryConstants.SmeltingFuelSlot, ItemStack.of(fuelType, fuelAmount - 1))
                smeltingInventory.setItemStack(InventoryConstants.SmeltingInputSlot, ItemStack.of(inputType, inputAmount - 1))
                selectedItems = initAir(selectedItems)
            }
            // scan for recipes, only if we have an input, and fuel item
            // scan for recipes
            for (z in recipes.indices) {
                val recipe: MinecraftSmeltingRecipe = recipes[z]

                // allow crafting stacks of items at a time
                // since only one item can be smelted at a time
                val neededItem = recipe.inputItem?.material()?.let { ItemStack.of(it, 1) }
                val currentItem =
                    selectedItems[InventoryConstants.SmeltingInputSlot]?.let { ItemStack.of(it.material(), 1) }

                if (currentItem?.equals(neededItem) == true && selectedItems[InventoryConstants.SmeltingFuelSlot]?.let {
                        fuelAccept(
                            it
                        )
                    } == true){
                    // Found recipe
                    recipeOutputID.set(recipe.recipeID)
                    playSound(targetPlayer, "minecraft:block.blastfurnace.fire_crackle", "MASTER", 500F, 1F)
                    recipe.outputItem?.let {
                        smeltingInventory.setItemStack(
                            InventoryConstants.SmeltingOutputSlot,
                            it
                        )
                    }
                    break
                } else {
                    recipeOutputID.set(null)
                    smeltingInventory.setItemStack(InventoryConstants.SmeltingOutputSlot, ItemStack.AIR)
                }
            }
        }
        player.openInventory(smeltingInventory)
    }
}