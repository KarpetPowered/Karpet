package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack

open class MinecraftRecipe {
    var recipeID: String? = null
    var portableItems // List of items used to craft the item in the portable table
            : List<ItemStack>? = null
    var items // List of items used to craft the item in the crafting table
        : List<ItemStack>? = null
    var result: ItemStack? = null
//    fun CanCraft(player: Player?): Boolean {
//        return false
//    }

    open fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}