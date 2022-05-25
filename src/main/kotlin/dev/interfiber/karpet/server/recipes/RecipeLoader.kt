package dev.interfiber.karpet.server.recipes

object RecipeLoader {
    private val LoadedRecipes: MutableList<MinecraftRecipe> = ArrayList()

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

    fun getLoadedRecipes(): List<MinecraftRecipe> {
        return LoadedRecipes
    }
}