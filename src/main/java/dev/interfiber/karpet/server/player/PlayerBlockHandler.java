package dev.interfiber.karpet.server.player;

import dev.interfiber.karpet.server.utils.Probability;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

/**
 *
 * @author persephone
 */
public class PlayerBlockHandler {
    public static void BreakBlockCallback(PlayerBlockBreakEvent BreakBlockEvent){
        Player EventPlayer = BreakBlockEvent.getPlayer();
        InstanceContainer PlayerInstanceContainer = (InstanceContainer) EventPlayer.getInstance();
        Block BrokenBlockType = BreakBlockEvent.getBlock();
        if (BrokenBlockType == Block.GRASS_BLOCK){
            PlayerBackpack.SpawnItemStack(Material.DIRT, EventPlayer.getPosition(), PlayerInstanceContainer);
        } else if (BrokenBlockType == Block.OAK_LOG) {
            PlayerBackpack.SpawnItemStack(Material.OAK_LOG, EventPlayer.getPosition(), PlayerInstanceContainer);
        } else if (BrokenBlockType == Block.OAK_PLANKS){
            PlayerBackpack.SpawnItemStack(Material.OAK_PLANKS, EventPlayer.getPosition(), PlayerInstanceContainer);
        } else if (BrokenBlockType == Block.CRAFTING_TABLE){
            PlayerBackpack.SpawnItemStack(Material.CRAFTING_TABLE, EventPlayer.getPosition(), PlayerInstanceContainer);
        } else if (BrokenBlockType == Block.GRASS || BrokenBlockType == Block.TALL_GRASS){
            boolean ShouldBreak = Probability.Calculate(12.5);
            if (ShouldBreak){
                PlayerBackpack.SpawnItemStack(Material.WHEAT_SEEDS, EventPlayer.getPosition(), PlayerInstanceContainer);
            }
        }
        EventPlayer.getInventory().update();
    }
}
