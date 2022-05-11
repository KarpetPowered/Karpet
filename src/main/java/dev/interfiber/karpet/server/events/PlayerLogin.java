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
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;

/**
 *
 * @author persephone
 */
public class PlayerLogin extends EventHandler {
    public static void FireEvent(PlayerLoginEvent event, InstanceContainer instanceContainer){
            final Player player = event.getPlayer();
            // Get spawn location
            SpawnLocation Location = SpawnLocator.GetSpawnLocation(instanceContainer);
            Audiences.all().sendMessage(Component.text(
                    player.getUsername() + " has joined the game",
                    NamedTextColor.GREEN
            ));
            Spawner EntitySpawner = new Spawner();
            EntityStats ZombieStats = new EntityStats();
            ZombieStats.AttackDamage = 100;
            ZombieStats.MaxHealth = 50;
            EntitySpawner.Stats = ZombieStats;
            EntitySpawner.SpawnEntity(EntityType.ZOMBIE, new Pos(Location.SpawnX, Location.SpawnY, Location.SpawnZ), instanceContainer);
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(Location.SpawnX, Location.SpawnY, Location.SpawnZ));
    }
}
