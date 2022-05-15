package dev.interfiber.karpet.server.player
import dev.interfiber.karpet.server.recipes.InventoryConstants
import dev.interfiber.karpet.server.recipes.MinecraftRecipe
import net.minestom.server.entity.Player
import net.minestom.server.inventory.click.ClickType
import net.minestom.server.inventory.condition.InventoryConditionResult
import net.minestom.server.item.ItemStack
import java.util.concurrent.atomic.AtomicReference


class PlayerCraftingHandler {
    private fun indexCraft(index: Int): Boolean {
        return index == InventoryConstants.PortableInventorySlot1 || index == InventoryConstants.PortableInventorySlot2 || index == InventoryConstants.PortableInventorySlot3 || index == InventoryConstants.PortableInventorySlot4
    }

    private fun craftClick(Type: ClickType): Boolean {
        return Type == ClickType.RIGHT_CLICK || Type == ClickType.LEFT_CLICK || Type == ClickType.RIGHT_DRAGGING || Type == ClickType.LEFT_DRAGGING
    }

    fun addCraftEvent(player: Player, Recipes: List<MinecraftRecipe>) {
        val selectedItems = HashMap<Int, ItemStack>()
        selectedItems[InventoryConstants.PortableInventorySlot1] = ItemStack.AIR
        selectedItems[InventoryConstants.PortableInventorySlot2] = ItemStack.AIR
        selectedItems[InventoryConstants.PortableInventorySlot3] = ItemStack.AIR
        selectedItems[InventoryConstants.PortableInventorySlot4] = ItemStack.AIR
        val recipeOutputID = AtomicReference("")
        player.inventory.addInventoryCondition { targetPlayer: Player, slot: Int, clickType: ClickType, inventoryConditionResult: InventoryConditionResult ->
            if (slot == InventoryConstants.PortableInventoryOutputSlot && selectedItems.size == 0) {
                // Clear the output slot
                targetPlayer.inventory.setItemStack(InventoryConstants.PortableInventoryOutputSlot, ItemStack.AIR)
                // Update & cancel
                targetPlayer.inventory.update()
                inventoryConditionResult.isCancel = true
                targetPlayer.sendMessage("Cant craft item: Crafting grid is empty")
            }
            if (slot == InventoryConstants.PortableInventoryOutputSlot) {
                // Clear memory of crafting
                selectedItems.clear()
                selectedItems[InventoryConstants.PortableInventorySlot1] = ItemStack.AIR
                selectedItems[InventoryConstants.PortableInventorySlot2] = ItemStack.AIR
                selectedItems[InventoryConstants.PortableInventorySlot3] = ItemStack.AIR
                selectedItems[InventoryConstants.PortableInventorySlot4] = ItemStack.AIR
                // Clear inventory
                targetPlayer.inventory.setItemStack(InventoryConstants.PortableInventorySlot1, ItemStack.AIR)
                targetPlayer.inventory.setItemStack(InventoryConstants.PortableInventorySlot2, ItemStack.AIR)
                targetPlayer.inventory.setItemStack(InventoryConstants.PortableInventorySlot3, ItemStack.AIR)
                targetPlayer.inventory.setItemStack(InventoryConstants.PortableInventorySlot4, ItemStack.AIR)
                // Update inventory
                player.inventory.update()
                // Log craft to console (Server admin can view everything being crafted)
                return@addInventoryCondition
            }
            if (indexCraft(slot) && craftClick(clickType)) {
                // Get the current clicked item
                val cursorItem = inventoryConditionResult.cursorItem
                if (cursorItem.isAir) {
                    selectedItems[slot] = ItemStack.AIR
                } else {
                    selectedItems[slot] = cursorItem
                }
                // Query all loaded recipes
                // Basically loop over all provided recipes, until we find one with the same item stucture as the current
                // crafting table, when we find it set the crafting table output item and break the loop. Once the item is clicked we clear the grid
                for (z in Recipes.indices) {
                    val recipe: MinecraftRecipe = Recipes[z]
                    if (recipe.CanCraftInPortableCrafting()) {
                        val values: Collection<ItemStack> = selectedItems.values
                        val itemsList: List<ItemStack> = ArrayList(values)
                        if (recipe.items?.equals(itemsList) == true) {
                            val resultItem: ItemStack? = recipe.result // TODO calculate the amount for items crafted
                            if (resultItem != null) {
                                targetPlayer.inventory
                                    .setItemStack(InventoryConstants.PortableInventoryOutputSlot, resultItem)
                            }
                            recipeOutputID.set(recipe.recipeID)
                            break
                        } else {
                            targetPlayer.inventory
                                .setItemStack(InventoryConstants.PortableInventoryOutputSlot, ItemStack.AIR)
                        }
                    }
                }
            }
            inventoryConditionResult.isCancel = false
        }
    }
}