package dev.interfiber.karpet.server.recipes

/**
 * Loads all recipes into memory
 * @author Interfiber
 */
object RecipeLoader {
    private val LoadedRecipes: MutableList<MinecraftRecipe> = ArrayList()
    /**
     * Load the recipes into memory
     * @author Interfiber
     */
    fun loadAllRecipes() {
        val oakPlanksRecipe = OakPlanksRecipe()
        val stickRecipe = StickRecipe()
        val craftingTableRecipe = CraftingTableRecipe()
        val woodenPickaxeRecipe = WoodenPickaxeRecipe()
        val stonePickaxeRecipe = StonePickaxeRecipe()
        val furnaceRecipe = FurnaceRecipe()
        val ironPickaxeRecipe = IronPickaxeRecipe()

        // Load all recipes
        LoadedRecipes.add(oakPlanksRecipe)
        LoadedRecipes.add(stickRecipe)
        LoadedRecipes.add(craftingTableRecipe)
        LoadedRecipes.add(woodenPickaxeRecipe)
        LoadedRecipes.add(stonePickaxeRecipe)
        LoadedRecipes.add(furnaceRecipe)
        LoadedRecipes.add(ironPickaxeRecipe)
    }
    /**
     * Get all loaded recipes loaded by loadAllRecipes()
     * @author Interfiber
     */
    fun getLoadedRecipes(): List<MinecraftRecipe> {
        return LoadedRecipes
    }
}