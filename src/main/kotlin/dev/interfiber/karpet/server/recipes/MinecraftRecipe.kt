package dev.interfiber.karpet.server.recipes

import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

open class MinecraftRecipe {
    var recipeID: String? = null
    var items // 6x6 square grid, for anything blank mark it as Material.AIR
            : List<ItemStack>? = null
    var result: ItemStack? = null
    fun CanCraft(player: Player?): Boolean {
        return false
    }

    open fun CanCraftInPortableCrafting(): Boolean {
        return false
    }
}