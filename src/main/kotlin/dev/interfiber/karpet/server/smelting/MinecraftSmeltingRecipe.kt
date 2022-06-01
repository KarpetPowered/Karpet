package dev.interfiber.karpet.server.smelting

import net.minestom.server.item.ItemStack

/**
 * Represents a smeltable recipe
 * @author Interfiber
 */
open class MinecraftSmeltingRecipe {
    var inputItem: ItemStack? = null
    var outputItem: ItemStack? = null
    var recipeID: String? = null
}
