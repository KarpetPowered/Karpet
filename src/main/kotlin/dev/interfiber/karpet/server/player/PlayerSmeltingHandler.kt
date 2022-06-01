package dev.interfiber.karpet.server.player

import dev.interfiber.karpet.server.recipes.InventoryConstants
import dev.interfiber.karpet.server.smelting.MinecraftSmeltingRecipe
import mu.KotlinLogging
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.inventory.condition.InventoryConditionResult
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.concurrent.atomic.AtomicReference

private val logger = KotlinLogging.logger {}

/**
 * Handle player smelting with a furnace
 * @author Interfiber
 */
class PlayerSmeltingHandler {

    private val recipeOutputID = AtomicReference("")

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
     * Get a smelting recipe by output ID
     * @author Interfiber
     */
    private fun getRecipeByID(recipeID: String, recipes: List<MinecraftSmeltingRecipe>) : MinecraftSmeltingRecipe? {
        var recipeOut: MinecraftSmeltingRecipe? = null
        for (recipe in recipes){
            if (recipe.recipeID?.lowercase() == recipeID.lowercase()){
                recipeOut = recipe
            }
        }
        return recipeOut
    }

    private fun furnaceEvent(
        targetPlayer: Player,
        slot: Int,
        inventoryConditionResult: InventoryConditionResult,
        smeltingInventory: Inventory,
        sItems: HashMap<Int, ItemStack>,
        recipes: List<MinecraftSmeltingRecipe>
    ){
        var selectedItems = sItems
        if (inventoryConditionResult.cursorItem != ItemStack.AIR){
            selectedItems[slot] = inventoryConditionResult.cursorItem
        } else {
            selectedItems[slot] = ItemStack.AIR
        }

        if (slot == InventoryConstants.SmeltingOutputSlot && recipeOutputID.get() != null){
            logger.info(targetPlayer.username + " smelted a " + recipeOutputID)
            selectedItems = initAir(selectedItems)


            smeltingInventory.setItemStack(InventoryConstants.SmeltingFuelSlot, ItemStack.AIR)
            smeltingInventory.setItemStack(InventoryConstants.SmeltingInputSlot, ItemStack.AIR)
            recipeOutputID.set(null)

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
                if (selectedItems[InventoryConstants.SmeltingInputSlot]?.amount() == selectedItems[InventoryConstants.SmeltingFuelSlot]?.amount()){
                    recipeOutputID.set(recipe.recipeID)
                    playSound(targetPlayer, "minecraft:block.blastfurnace.fire_crackle", "MASTER", 500F, 1F)
                    val outputItemStack = recipe.outputItem?.let { selectedItems[InventoryConstants.SmeltingInputSlot]?.amount()
                        ?.let { it1 -> ItemStack.of(it.material(), it1) } }
                    if (outputItemStack != null) {
                        smeltingInventory.setItemStack(
                            InventoryConstants.SmeltingOutputSlot,
                            outputItemStack
                        )
                    }
                    break
                } else {
                    logger.warn("Fuel, and input amount do not match")
                    println(smeltingInventory.getItemStack(InventoryConstants.SmeltingInputSlot).amount())
                    println(smeltingInventory.getItemStack(InventoryConstants.SmeltingFuelSlot).amount())
                    recipeOutputID.set(null)
                    println(selectedItems)
                }
            } else {
                recipeOutputID.set(null)
                smeltingInventory.setItemStack(InventoryConstants.SmeltingOutputSlot, ItemStack.AIR)
            }
        }
    }

    /**
     * Called when a player opens the furnace
     * @author Interfiber
     */
    fun addFurnaceEvent(player: Player, recipes: List<MinecraftSmeltingRecipe>){
        val smeltingInventory = Inventory(InventoryType.FURNACE, "Smelting")
        var selectedItems = HashMap<Int, ItemStack>()
        selectedItems = initAir(selectedItems)
        smeltingInventory.addInventoryCondition { targetPlayer, slot, _, inventoryConditionResult ->
            furnaceEvent(targetPlayer, slot, inventoryConditionResult, smeltingInventory, selectedItems, recipes)
        }
        player.openInventory(smeltingInventory)
    }
}