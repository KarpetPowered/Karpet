package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class IronPickaxeRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "iron_pickaxe"
        // anything that is a * is taken
        // * * * <- row one
        // x * x <- row two
        // x * x <- row three
        this.items = listOf(
            ItemStack.of(Material.IRON_INGOT, 1), // *
            ItemStack.of(Material.IRON_INGOT, 1), // *
            ItemStack.of(Material.IRON_INGOT, 1), // *
            ItemStack.AIR, // x
            ItemStack.of(Material.STICK, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.of(Material.STICK, 1), // *
            ItemStack.AIR, // x
        )
        this.result = ItemStack.of(Material.IRON_PICKAXE, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}