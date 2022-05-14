package dev.interfiber.karpet.server.recipes;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket.Ingredient;

import java.util.List;

public class CraftingTableRecipe extends MinecraftRecipe {
    public CraftingTableRecipe(){
        this.RecipeID = "crafting_table";
        this.Items = List.of(
                ItemStack.of(Material.OAK_PLANKS, 1),
                ItemStack.of(Material.OAK_PLANKS, 1),
                ItemStack.of(Material.OAK_PLANKS, 1),
                ItemStack.of(Material.OAK_PLANKS, 1)
        );
        this.Result = ItemStack.of(Material.CRAFTING_TABLE, 1);
    }
    @Override
    public boolean CanCraftInPortableCrafting(){
        return true;
    }
}
