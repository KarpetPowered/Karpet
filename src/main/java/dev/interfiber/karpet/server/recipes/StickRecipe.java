package dev.interfiber.karpet.server.recipes;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket.Ingredient;

import java.util.List;

public class StickRecipe extends MinecraftRecipe {
    public StickRecipe(){
        this.RecipeID = "stick";
        this.Items = List.of(
                ItemStack.of(Material.OAK_PLANKS, 1),
                ItemStack.AIR,
                ItemStack.of(Material.OAK_PLANKS, 1),
                ItemStack.AIR
        );
        this.Result = ItemStack.of(Material.STICK, 4);
    }
    @Override
    public boolean CanCraftInPortableCrafting(){
        return true;
    }
}
