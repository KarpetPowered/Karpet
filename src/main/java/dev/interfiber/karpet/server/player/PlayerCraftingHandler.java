package dev.interfiber.karpet.server.player;

import dev.interfiber.karpet.KarpetLauncher;
import dev.interfiber.karpet.server.recipes.MinecraftRecipe;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.click.ClickType;
import dev.interfiber.karpet.server.recipes.InventoryConstants;
import net.minestom.server.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author persephone
 */
public class PlayerCraftingHandler {
    private static final Logger LOG = LogManager.getLogger(KarpetLauncher.class);
    private static boolean IndexCraft(int index){
        if (index == InventoryConstants.PortableInventorySlot1 || index == InventoryConstants.PortableInventorySlot2 || index == InventoryConstants.PortableInventorySlot3 || index == InventoryConstants.PortableInventorySlot4){
            return true;
        } else {
            return false;
        }
    }
    private static boolean CraftClick(ClickType Type){
        if (Type == ClickType.RIGHT_CLICK || Type == ClickType.LEFT_CLICK){
            return true;
        } else {
            return false;
        }
    }
    public static void AddCraftEvent(Player player, List<MinecraftRecipe> Recipes){
        HashMap<Integer, ItemStack> SelectedItems = new HashMap<Integer, ItemStack>();
        SelectedItems.put(InventoryConstants.PortableInventorySlot1, ItemStack.AIR);
        SelectedItems.put(InventoryConstants.PortableInventorySlot2, ItemStack.AIR);
        SelectedItems.put(InventoryConstants.PortableInventorySlot3, ItemStack.AIR);
        SelectedItems.put(InventoryConstants.PortableInventorySlot4, ItemStack.AIR);
        AtomicReference<String> RecipeOutputId = new AtomicReference<>("");
        player.getInventory().addInventoryCondition((targetPlayer, slot, clickType, inventoryConditionResult) -> {
            if (slot == InventoryConstants.PortableInventoryOutputSlot && SelectedItems.size() == 0){
                // Clear the output slot
                targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventoryOutputSlot, ItemStack.AIR);
                // Update & cancel
                targetPlayer.getInventory().update();
                inventoryConditionResult.setCancel(true);
                targetPlayer.sendMessage("Cant craft item: Crafting grid is empty");
            }
            if (slot == InventoryConstants.PortableInventoryOutputSlot){
                LOG.info("player with uuid: " + targetPlayer.getUuid() + " crafted " + RecipeOutputId.get());
                // Clear memory of crafting
                SelectedItems.clear();
                SelectedItems.put(InventoryConstants.PortableInventorySlot1, ItemStack.AIR);
                SelectedItems.put(InventoryConstants.PortableInventorySlot2, ItemStack.AIR);
                SelectedItems.put(InventoryConstants.PortableInventorySlot3, ItemStack.AIR);
                SelectedItems.put(InventoryConstants.PortableInventorySlot4, ItemStack.AIR);
                // Clear inventory
                targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot1, ItemStack.AIR);
                targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot2, ItemStack.AIR);
                targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot3, ItemStack.AIR);
                targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventorySlot4, ItemStack.AIR);
                // Update inventory
                player.getInventory().update();
                // Log craft to console (Server admin can view everything being crafted)
                return;
            }
            if (IndexCraft(slot) && CraftClick(clickType)){
                // Get the current clicked item
                ItemStack CursorItem = inventoryConditionResult.getCursorItem();
                if (CursorItem.isAir()){
                    SelectedItems.put(slot, ItemStack.AIR);
                } else {
                    SelectedItems.put(slot, CursorItem);
                }
                System.out.println(SelectedItems);
                // Query all loaded recipes
                // Basically loop over all provided recipes, until we find one with the same item stucture as the current
                // crafting table, when we find it set the crafting table output item and break the loop. Once the item is clicked we clear the grid
                for (int z = 0; z < Recipes.size(); z++){
                    MinecraftRecipe Recipe = Recipes.get(z);
                    if (Recipe.CanCraftInPortableCrafting()){
                        Collection<ItemStack> Values = SelectedItems.values();
                        List<ItemStack> ItemsList = new ArrayList<ItemStack>(Values);
                        if (Recipe.Items.equals(ItemsList)){
                            ItemStack ResultItem = Recipe.Result; // TODO calculate the amount for items crafted
                            targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventoryOutputSlot, ResultItem);
                            RecipeOutputId.set(Recipe.RecipeID);
                            break;
                        } else {
                            targetPlayer.getInventory().setItemStack(InventoryConstants.PortableInventoryOutputSlot, ItemStack.AIR);
                        }
                    }
                }

            }
            inventoryConditionResult.setCancel(false);
        });
    }
}
