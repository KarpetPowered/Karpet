package dev.interfiber.karpet.server.recipes

import net.minestom.server.recipe.RecipeManager

object RecipeLoader {
    private val LoadedRecipes: MutableList<MinecraftRecipe> = ArrayList()

    fun loadAllRecipes() {
        val oakPlanksRecipe = OakPlanksRecipe()
//        val CraftingTable = CraftingTableRecipe()
//        val Stick = StickRecipe()
        // Load all recipes
        LoadedRecipes.add(oakPlanksRecipe)
//        LoadedRecipes.add(CraftingTable)
//        LoadedRecipes.add(Stick)
    }

    fun getLoadedRecipes(): List<MinecraftRecipe> {
        return LoadedRecipes
    }
}