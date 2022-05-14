package dev.interfiber.karpet.server.recipes;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket.Ingredient;

import java.util.List;

public class OakPlanksRecipe extends MinecraftRecipe {
    public OakPlanksRecipe(){
        this.RecipeID = "oak_planks";
        this.Items = List.of(
                ItemStack.of(Material.OAK_LOG, 1)
        );
        this.Result = ItemStack.of(Material.OAK_PLANKS, 1);
    }
    @Override
    public boolean CanCraftInPortableCrafting(){
        return true;
    }
}
