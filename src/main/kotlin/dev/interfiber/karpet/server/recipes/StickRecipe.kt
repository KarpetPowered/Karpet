package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class StickRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "stick"
        this.items = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.AIR,
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.AIR
        )
        this.result = ItemStack.of(Material.STICK, 4)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return true
    }
}