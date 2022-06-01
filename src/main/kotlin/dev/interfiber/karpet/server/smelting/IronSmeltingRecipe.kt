package dev.interfiber.karpet.server.smelting

import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

/**
 * Recipe to smelt a iron ingot
 * @author Interfiber
 */
class IronSmeltingRecipe : MinecraftSmeltingRecipe() {
    init {
        this.inputItem = ItemStack.of(Material.RAW_IRON)
        this.outputItem = ItemStack.of(Material.IRON_INGOT)
        this.recipeID = "iron_ingot"
    }
}