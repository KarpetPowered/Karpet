package dev.interfiber.karpet.server.recipes;

import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket.Ingredient;

import java.util.List;

public class MinecraftRecipe {
    public String RecipeID;
    public List<ItemStack> Items; // 6x6 square grid, for anything blank mark it as Material.AIR
    public ItemStack Result;

    public boolean CanCraft(Player player) {
        return false;
    }
    public boolean CanCraftInPortableCrafting() { return false; }
}
