package dev.interfiber.karpet.server.recipes

import net.minestom.server.recipe.RecipeManager

object RecipeLoader {
    private val LoadedRecipes: MutableList<MinecraftRecipe> = ArrayList()

    fun loadAllRecipes() {
        val oakPlanksRecipe = OakPlanksRecipe()
        val stickRecipe = StickRecipe()
        val craftingTableRecipe = CraftingTableRecipe()
        // Load all recipes
        LoadedRecipes.add(oakPlanksRecipe)
        LoadedRecipes.add(stickRecipe)
        LoadedRecipes.add(craftingTableRecipe)
    }

    fun getLoadedRecipes(): List<MinecraftRecipe> {
        return LoadedRecipes
    }
}