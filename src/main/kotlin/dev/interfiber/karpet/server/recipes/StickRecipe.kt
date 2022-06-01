package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
/**
 * Recipe to craft a stick
 * @author Interfiber
 */
class StickRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "stick"
        this.portableItems = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.AIR,
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.AIR
        )
        // anything that is a * is taken
        // * x x <- row one
        // * x x <- row two
        // x x x <- row three
        this.items = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
        )
        this.result = ItemStack.of(Material.STICK, 4)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return true
    }
}