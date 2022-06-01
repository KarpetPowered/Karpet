package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

/**
 * Recipe to craft a wooden pickaxe
 * @author Interfiber
 */
class WoodenPickaxeRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "wooden_pickaxe"
        // anything that is a * is taken
        // * * * <- row one
        // x * x <- row two
        // x * x <- row three
        this.items = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.AIR, // x
            ItemStack.of(Material.STICK, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.of(Material.STICK, 1), // *
            ItemStack.AIR, // x
        )
        this.result = ItemStack.of(Material.WOODEN_PICKAXE, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}
