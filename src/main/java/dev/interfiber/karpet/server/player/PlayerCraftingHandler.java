package dev.interfiber.karpet.server.player;

import dev.interfiber.karpet.server.recipes.MinecraftRecipe;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.advancements.FrameType;
import net.minestom.server.advancements.notifications.Notification;
import net.minestom.server.advancements.notifications.NotificationCenter;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.click.ClickType;
import dev.interfiber.karpet.server.recipes.InventoryConstants;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author persephone
 */
public class PlayerCraftingHandler {
    private static boolean IndexCraft(int index){
        if (index == InventoryConstants.PortableInventorySlot1 || index == InventoryConstants.PortableInventorySlot2 || index == InventoryConstants.PortableInventorySlot3 || index == InventoryConstants.PortableInventorySlot4){
            return true;
        } else {
            return false;
        }
    }
    public static void AddCraftEvent(Player player, List<MinecraftRecipe> Recipes){
        HashMap<Integer, ItemStack> SelectedItems = new HashMap<Integer, ItemStack>();
        final ItemStack[] Output = {ItemStack.of(Material.AIR)};
        player.getInventory().addInventoryCondition((targetPlayer, slot, clickType, inventoryConditionResult) -> {
            System.out.println(slot);
            if (slot == InventoryConstants.PortableInventoryOutputSlot){
                if (Output[0] != ItemStack.AIR || Output[0].material() != Material.AIR){
                    // Clear memory of crafting
                    SelectedItems.clear();
                    // Clear inventory
                    targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot1, ItemStack.AIR);
                    targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot2, ItemStack.AIR);
                    targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot3, ItemStack.AIR);
                    targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot4, ItemStack.AIR);
                    // Update inventory
                    player.getInventory().update();
                }
                return;
            }
            // Only register left clicks, and right clicks
            if (clickType == ClickType.LEFT_CLICK || clickType == ClickType.RIGHT_CLICK && IndexCraft(slot)){
                // Get the current clicked item
                ItemStack CursorItem = inventoryConditionResult.getCursorItem();
                if (CursorItem.isAir()){
                    SelectedItems.remove(slot);
                } else {
                    SelectedItems.put(slot, CursorItem);
                }
                // Debug, TODO remove
                // Query all loaded recipes
                // Basically loop over all provided recipes, until we find one with the same item stucture as the current
                // crafting table, when we find it set the crafting table output item and break the loop. Once the item is clicked we clear the grid
                for (int z = 0; z < Recipes.size(); z++){
                    MinecraftRecipe Recipe = Recipes.get(z);
                    if (Recipe.CanCraftInPortableCrafting()){
                        Collection<ItemStack> Values = SelectedItems.values();
                        ArrayList<ItemStack> ItemsList = new ArrayList<ItemStack>(Values);
                        if (ItemsList.containsAll(Recipe.Items)){
                            System.out.println(Recipe.RecipeID + " matches!");
                            ItemStack ResultItem = Recipe.Result; // TODO calculate the amount for items crafted
                            targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventoryOutputSlot, ResultItem);
                            break;
                        }
                    }
                }

            }
            inventoryConditionResult.setCancel(false);
        });
    }
}
