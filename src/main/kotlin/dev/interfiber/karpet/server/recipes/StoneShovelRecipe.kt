package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
/**
 * Recipe to craft a stone shovel
 * @author Interfiber
 */
class StoneShovelRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "stone_shovel"
        // anything that is a * is taken
        // x * x <- row one
        // x * x <- row two
        // x * x <- row three
        this.items = listOf(
            ItemStack.AIR,
            ItemStack.of(Material.COBBLESTONE, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.of(Material.STICK, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.of(Material.STICK, 1),
            ItemStack.AIR,
            )
        this.result = ItemStack.of(Material.STONE_SHOVEL, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}
