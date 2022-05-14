package dev.interfiber.karpet.server.recipes;

import dev.interfiber.karpet.KarpetLauncher;

import java.util.ArrayList;
import java.util.List;

import dev.interfiber.karpet.server.player.PlayerCraftingHandler;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket.Ingredient;
import net.minestom.server.recipe.RecipeManager;
import net.minestom.server.utils.NamespaceID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecipeLoader {
    private static List<MinecraftRecipe> LoadedRecipes = new ArrayList<MinecraftRecipe>();
    private static final Logger LOG = LogManager.getLogger(KarpetLauncher.class);
    public static void LoadAllRecipes(RecipeManager Manager){
        // TODO
        LOG.info("Loading recipes...");
        OakPlanksRecipe OakPlanks = new OakPlanksRecipe();
        CraftingTableRecipe CraftingTable = new CraftingTableRecipe();
        StickRecipe Stick = new StickRecipe();
        // Load all recipes
        LoadedRecipes.add(OakPlanks);
        LoadedRecipes.add(CraftingTable);
        LoadedRecipes.add(Stick);
    }
    public static List<MinecraftRecipe> GetLoadedRecipes(){
        return LoadedRecipes;
    }
}
