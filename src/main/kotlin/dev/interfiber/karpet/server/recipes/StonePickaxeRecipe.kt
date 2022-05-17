package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class StonePickaxeRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "stone_pickaxe"
        // anything that is a * is taken
        // * * * <- row one
        // x * x <- row two
        // x * x <- row three
        this.items = listOf(
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.AIR, // x
            ItemStack.of(Material.STICK, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.of(Material.STICK, 1), // *
            ItemStack.AIR, // x
        )
        this.result = ItemStack.of(Material.STONE_PICKAXE, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}