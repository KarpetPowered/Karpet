package dev.interfiber.karpet.server.smelting

object SmeltingRecipeLoader {
    private val LoadedRecipes: MutableList<MinecraftSmeltingRecipe> = ArrayList()

    fun loadAllRecipes() {
        val ironSmeltingRecipe = IronSmeltingRecipe()
        LoadedRecipes.add(ironSmeltingRecipe)
    }

    fun getLoadedRecipes(): List<MinecraftSmeltingRecipe> {
        return LoadedRecipes
    }
}