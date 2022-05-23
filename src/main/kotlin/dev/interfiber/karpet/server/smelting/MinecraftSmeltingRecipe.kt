package dev.interfiber.karpet.server.smelting

import net.minestom.server.item.ItemStack

open class MinecraftSmeltingRecipe {
    var inputItem: ItemStack? = null
    var outputItem: ItemStack? = null
    var recipeID: String? = null
}