package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class CraftingTableRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "crafting_table"
        this.items = listOf(
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.of(Material.OAK_PLANKS, 1),
        )
        this.result = ItemStack.of(Material.CRAFTING_TABLE, 4)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return true
    }
}