package dev.interfiber.karpet.server.commands

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.ArgumentType

/**
 * Manage player permissons
 * @param instance The instance to save
 * @author Interfiber
 */

class GivePermissonCommand() : Command("givepermission", "grantpermission", "giveperm", "grantperm") {
    init {

        // Executed if no other executor can be used
        defaultExecutor =
            CommandExecutor { sender: CommandSender, _: CommandContext? ->

                if (sender.hasPermission("karpet.commands.permission.manage")) {
                } else {
                    sender.sendMessage("[KARPET] Failed to run permission update! Insufficient permissions")
                }
            }
        val playerName = ArgumentType.String("player-name")
        val permission = ArgumentType.String("permission")
        val remove = ArgumentType.Boolean("remove")
        addSyntax({ sender: CommandSender, context: CommandContext ->
            val player: String = context.get(playerName)
            val perm: String = context.get(permission)
            val shouldRemove: Boolean = context.get(remove)
            sender.sendMessage("[KARPET] Running permission update:\n Player to grant: $player\nPermission to grant: $perm\nShould remove: $shouldRemove")
        }, playerName, permission, remove)
    }
}
