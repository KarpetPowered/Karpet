package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class FurnaceRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "furnace"
        // anything that is a * is taken
        // * * * <- row one
        // * x * <- row two
        // * * * <- row three
        this.items = listOf(
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.AIR, // x
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
            ItemStack.of(Material.COBBLESTONE, 1), // *
        )
        this.result = ItemStack.of(Material.FURNACE, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}