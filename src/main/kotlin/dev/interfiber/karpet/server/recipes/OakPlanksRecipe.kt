package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class OakPlanksRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "oak_planks"
        this.portableItems = listOf(
            ItemStack.of(Material.OAK_LOG, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.AIR
        )
        // anything that is a * is taken
        // * x x <- row one
        // x x x <- row two
        // x x x <- row three
        this.items = listOf(
            ItemStack.of(Material.OAK_LOG, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
        )
        this.result = ItemStack.of(Material.OAK_PLANKS, 4)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return true
    }
}