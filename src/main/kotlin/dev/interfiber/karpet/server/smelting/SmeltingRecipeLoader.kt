package dev.interfiber.karpet.server.smelting

/**
 * Load all smelting recipes into memory
 * @author Interfiber
 */
object SmeltingRecipeLoader {
    private val LoadedRecipes: MutableList<MinecraftSmeltingRecipe> = ArrayList()
    /**
     * Load the smelting recipes into memory
     * @author Interfiber
     */
    fun loadAllRecipes() {
        val ironSmeltingRecipe = IronSmeltingRecipe()
        LoadedRecipes.add(ironSmeltingRecipe)
    }
    /**
     * Return all recipes loaded by loadAllRecipes()
     * @author Interfiber
     */

    fun getLoadedRecipes(): List<MinecraftSmeltingRecipe> {
        return LoadedRecipes
    }
}