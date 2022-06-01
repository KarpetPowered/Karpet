package dev.interfiber.karpet.server.player

import dev.interfiber.karpet.server.recipes.InventoryConstants
import dev.interfiber.karpet.server.recipes.MinecraftRecipe
import mu.KotlinLogging
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.inventory.click.ClickType
import net.minestom.server.inventory.condition.InventoryConditionResult
import net.minestom.server.item.ItemStack
import java.util.concurrent.atomic.AtomicReference


private val logger = KotlinLogging.logger {}

/**
 * Player crafting table crafting system
 * @author Interfiber
 */
class PlayerCraftingTableHandler {
    /**
     * Check if a slot id is in the crafting grid
     * @author Interfiber
     */
    private fun indexCraft(index: Int): Boolean {
        return index == InventoryConstants.CraftingInventorySlot1 || index == InventoryConstants.CraftingInventorySlot2 || index == InventoryConstants.CraftingInventorySlot3 || index == InventoryConstants.CraftingInventorySlot4 || index == InventoryConstants.CraftingInventorySlot5 || index == InventoryConstants.CraftingInventorySlot6 || index == InventoryConstants.CraftingInventorySlot7 || index == InventoryConstants.CraftingInventorySlot8 || index == InventoryConstants.CraftingInventorySlot9
    }
    /**
     * Check if a click type is accepted in the crafting grid
     * @author Interfiber
     */

    private fun craftClick(Type: ClickType): Boolean {
        return Type == ClickType.RIGHT_CLICK || Type == ClickType.LEFT_CLICK || Type == ClickType.RIGHT_DRAGGING || Type == ClickType.LEFT_DRAGGING
    }
    /**
     * Fill an entire Hashmap with air
     * @author Interfiber
     */
    private fun initAir(selectedItems: HashMap<Int, ItemStack>): HashMap<Int, ItemStack> {
        selectedItems[InventoryConstants.CraftingInventorySlot1] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot2] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot3] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot4] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot5] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot6] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot7] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot8] = ItemStack.AIR
        selectedItems[InventoryConstants.CraftingInventorySlot9] = ItemStack.AIR
        return selectedItems
    }
    /**
     * Called when a player opens a crafting table
     * @author Interfiber
     */
    fun addCraftingTableHandler(player: Player, Recipes: List<MinecraftRecipe>) {
        val craftingTableInventory = Inventory(InventoryType.CRAFTING, "Crafting Table")
        var selectedItems = HashMap<Int, ItemStack>()
        selectedItems = initAir(selectedItems)
        val recipeOutputID = AtomicReference("")
        craftingTableInventory.addInventoryCondition { _: Player, slot: Int, _: ClickType, inventoryConditionResult: InventoryConditionResult ->
            if (slot == InventoryConstants.CraftingInventoryOutputSlot && recipeOutputID.get() != null) {
                // Clear memory of crafting
                selectedItems.clear()
                selectedItems = initAir(selectedItems)
                // Clear inventory
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot1, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot2, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot3, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot4, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot5, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot6, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot7, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot8, ItemStack.AIR)
                craftingTableInventory.setItemStack(InventoryConstants.CraftingInventorySlot9, ItemStack.AIR)
                // Update inventory
                craftingTableInventory.update()
                // Log craft to console (Server admin can view everything being crafted)
                logger.info(player.username + " crafted a $recipeOutputID")
                return@addInventoryCondition
            }
            if (indexCraft(slot)) {
                // Get the current clicked item
                val cursorItem = inventoryConditionResult.cursorItem
                if (cursorItem.isAir) {
                    selectedItems[slot] = ItemStack.AIR
                } else {
                    selectedItems[slot] = ItemStack.of(cursorItem.material(), 1)
                }
                // Query all loaded recipes
                // Basically loop over all provided recipes, until we find one with the same item stucture as the current
                // crafting table, when we find it set the crafting table output item and break the loop. Once the item is clicked we clear the grid
                for (z in Recipes.indices) {
                    val recipe: MinecraftRecipe = Recipes[z]
                    val values: Collection<ItemStack> = selectedItems.values
                    val itemsList: List<ItemStack> = ArrayList(values)
                    if (recipe.items?.equals(itemsList) == true) {
                        val resultItem: ItemStack? = recipe.result // TODO calculate the amount for items crafted
                        if (resultItem != null) {
                            craftingTableInventory
                                .setItemStack(InventoryConstants.CraftingInventoryOutputSlot, resultItem)
                        }
                        recipeOutputID.set(recipe.recipeID)
                        break
                    } else {
                        craftingTableInventory
                            .setItemStack(InventoryConstants.CraftingInventoryOutputSlot, ItemStack.AIR)
                        recipeOutputID.set(null)
                    }
                }
            }
            inventoryConditionResult.isCancel = false
        }
        player.openInventory(craftingTableInventory)
    }
}