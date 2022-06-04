package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
/**
 * Recipe to craft a wooden shovel
 * @author Interfiber
 */
class WoodenShovelRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "wooden_shovel"
        // anything that is a * is taken
        // x * x <- row one
        // x * x <- row two
        // x * x <- row three
        this.items = listOf(
            ItemStack.AIR,
            ItemStack.of(Material.OAK_PLANKS, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.of(Material.STICK, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.of(Material.STICK, 1),
            ItemStack.AIR,
            )
        this.result = ItemStack.of(Material.WOODEN_SHOVEL, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}
