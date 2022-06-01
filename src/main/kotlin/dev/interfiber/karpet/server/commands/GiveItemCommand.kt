package dev.interfiber.karpet.server.commands

import dev.interfiber.karpet.server.player.PlayerBackpack
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.item.Material
import net.minestom.server.utils.NamespaceID
import javax.swing.text.Position

/**
 * Manage player permissons
 * @param instance The instance to save
 * @author Interfiber
 */

class GiveItemCommand() : Command("give") {
    init {

        // Executed if no other executor can be used
        defaultExecutor =
            CommandExecutor { _: CommandSender, _: CommandContext? ->

            }
        val materialParam = ArgumentType.String("material")
        addSyntax({ sender: CommandSender, context: CommandContext  ->
            val materialString: String = context.get("material")
            val material = Material.fromNamespaceId(NamespaceID.from("minecraft:$materialString"))
            PlayerBackpack().spawnItemStack(material, (sender as Player).position, sender.instance as InstanceContainer?)
        }, materialParam)
    }
}