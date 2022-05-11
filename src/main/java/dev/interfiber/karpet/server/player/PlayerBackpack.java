package dev.interfiber.karpet.server.player;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

/**
 *
 * @author persephone
 */
public class PlayerBackpack {
    
    public static void SpawnItemStack(Material Type, Pos Position, InstanceContainer Container){
        ItemEntity Item = new ItemEntity(ItemStack.of(Type));
        Item.setInstance(Container, Position);
    }
    public static void PickupItemCallback(PickupItemEvent PickupEvent){
        Player TargetPlayer = (Player) PickupEvent.getEntity();
        Material ItemType = PickupEvent.getItemStack().material();
        TargetPlayer.getInventory().addItemStack(ItemStack.of(ItemType, 1));
    }
}
