package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack

/**
 * Represent a craftable recipe
 * @author Interfiber
 */
open class MinecraftRecipe {
    /**
     * A unique id to represent the recipe
     * @author Interfiber
     */
    var recipeID: String? = null
    /**
     * List of items used to craft the item in the portable table
     * @author Interfiber
     */
    var portableItems
            : List<ItemStack>? = null
    /**
     * List of items used to craft the item in the crafting table
     * @author Interfiber
     */
    var items
        : List<ItemStack>? = null
    /**
     * The output of the crafting recipe
     * @author Interfiber
     */
    var result: ItemStack? = null
//    fun CanCraft(player: Player?): Boolean {
//        return false
//    }

    open fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}