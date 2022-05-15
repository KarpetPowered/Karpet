package dev.interfiber.karpet.server.recipes

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import java.util.List

class OakPlanksRecipe : MinecraftRecipe() {
    init {
        this.recipeID = "oak_planks"
        this.items = List.of(
            ItemStack.of(Material.OAK_LOG, 1),
            ItemStack.AIR,
            ItemStack.AIR,
            ItemStack.AIR
        )
        this.result = ItemStack.of(Material.OAK_PLANKS, 4)
    }

    override fun CanCraftInPortableCrafting(): Boolean {
        return true
    }
}