package dev.interfiber.karpet.server.init;

import dev.interfiber.karpet.KarpetLauncher;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author persephone
 */
public class ServerBootstrap {
    private static final Logger LOG = LogManager.getLogger(KarpetLauncher.class);
    public static void StartServer(){
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        LOG.info("Loading world...");
        instanceContainer.setChunkLoader(new AnvilLoader("world"));
        LOG.info("Adding event handler");
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            LOG.info(player.getName() + " joined the game");
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 200, 0));
        });
        LOG.info("Binding server to port...");
        minecraftServer.start("0.0.0.0", 25565);
    }
}
