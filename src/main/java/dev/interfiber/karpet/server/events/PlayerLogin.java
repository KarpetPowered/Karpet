package dev.interfiber.karpet.server.events;

import dev.interfiber.karpet.server.player.PlayerBackpack;
import dev.interfiber.karpet.server.player.PlayerBlockHandler;
import dev.interfiber.karpet.server.player.PlayerCraftingHandler;
import dev.interfiber.karpet.server.recipes.RecipeLoader;
import dev.interfiber.karpet.server.utils.SpawnLocation;
import dev.interfiber.karpet.server.utils.SpawnLocator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.event.item.PickupItemEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

/**
 *
 * @author persephone
 */
public class PlayerLogin extends EventHandler {
    public static void FireEvent(PlayerLoginEvent event, InstanceContainer instanceContainer){
            final Player player = event.getPlayer();
            
            event.setSpawningInstance(instanceContainer);
            // Get spawn location
            SpawnLocation Location = SpawnLocator.GetSpawnLocation(instanceContainer);
            player.setRespawnPoint(new Pos(Location.SpawnX, Location.SpawnY, Location.SpawnZ));
            player.eventNode().addListener(EntityDamageEvent.class, damageEvent -> {
                // TODO: Calculate in protection from armor
            });
            player.eventNode().addListener(PickupItemEvent.class, PlayerBackpack::PickupItemCallback);
            player.eventNode().addListener(PlayerBlockBreakEvent.class, PlayerBlockHandler::BreakBlockCallback);
            PlayerCraftingHandler.AddCraftEvent(player, RecipeLoader.GetLoadedRecipes());
            Audiences.all().sendMessage(Component.text(
                    player.getUsername() + " has joined the game",
                    NamedTextColor.GREEN
            ));
        
    }
}
