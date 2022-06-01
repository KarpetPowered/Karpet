package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

/**
 * Recipe to craft a crafting table
 * @author Interfiber
 */
class CraftingTableRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "crafting_table"
        this.portableItems = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.of(Material.OAK_PLANKS, 1),
        )
        // anything that is a * is taken
        // * * x <- row one
        // * * x <- row two
        // x x x <- row three
        this.items = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.AIR, // x
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.of(Material.OAK_PLANKS, 1), // *
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
            ItemStack.AIR, // x
        )
        this.result = ItemStack.of(Material.CRAFTING_TABLE, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return true
    }
}
