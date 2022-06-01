package dev.interfiber.karpet.server.commands

import dev.interfiber.karpet.server.WorldSaveThread
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.instance.InstanceContainer

/**
 * Server save world command
 * Triggers an on-demand world save
 * @author Interfiber
 */

class SaveWorldCommand(instance: InstanceContainer) : Command("saveworld", "sw") {
    init {

        // Executed if no other executor can be used
        defaultExecutor =
            CommandExecutor { sender: CommandSender, _: CommandContext? ->
                sender.sendMessage("[KARPET] Running on-demand world save, will not report when done")
                val worldSaveThread = WorldSaveThread(instance)
                val thread = Thread(worldSaveThread)
                thread.start()
            }
    }
}