package dev.interfiber.karpet.server.blocks;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author persephone
 */
public class GrassBlock implements BlockHandler {
    @Override
    public void onPlace(@NotNull Placement placement) {
        if (placement instanceof PlayerPlacement) {
            // A player placed the block
        }
        Block block = placement.getBlock();
        System.out.println("The block " + block.name() + " has been placed");
    }
    @Override
    public @NotNull NamespaceID getNamespaceId() {
        // Namespace required for serialization purpose
        return NamespaceID.from("minecraft:grassblock");
    }
}
