package dev.interfiber.karpet.server.events;

import dev.interfiber.karpet.server.entitys.EntityStats;
import dev.interfiber.karpet.server.utils.SpawnLocation;
import dev.interfiber.karpet.server.utils.SpawnLocator;
import dev.interfiber.karpet.server.utils.Spawner;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.event.entity.EntityDamageEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;

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
            Audiences.all().sendMessage(Component.text(
                    player.getUsername() + " has joined the game",
                    NamedTextColor.GREEN
            ));
            Spawner EntitySpawner = new Spawner();
            EntityStats ZombieStats = new EntityStats();
            ZombieStats.AttackDamage = 100;
            ZombieStats.MaxHealth = 2; // as close to vanilla as possible without using a double
            EntitySpawner.Stats = ZombieStats;
            EntitySpawner.SpawnEntity(EntityType.ZOMBIE, new Pos(Location.SpawnX, Location.SpawnY, Location.SpawnZ), instanceContainer);
    }
}
