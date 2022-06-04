package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
/**
 * Recipe to craft an iron shovel
 * @author Interfiber
 */
class IronShovelRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "iron_shovel"
        // anything that is a * is taken
        // x * x <- row one
        // x * x <- row two
        // x * x <- row three
        this.items = listOf(
            ItemStack.AIR,
            ItemStack.of(Material.IRON_INGOT, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.of(Material.STICK, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.of(Material.STICK, 1),
            ItemStack.AIR,
            )
        this.result = ItemStack.of(Material.IRON_SHOVEL, 1)
    }

    override fun canCraftInPortableCrafting(): Boolean {
        return false
    }
}
