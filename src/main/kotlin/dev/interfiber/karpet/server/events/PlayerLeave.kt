package dev.interfiber.karpet.server.events

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.entity.Player

/**
 * Player leave event, called when a player disconnects from the server
 * @author Interfiber
 */
class PlayerLeave {
    /**
     * Fire the event
     * @author Interfiber
     */
    fun fireEvent(player: Player){
        Audiences.all().sendMessage(
            Component.text(
                player.username + " left the game",
                NamedTextColor.RED
            )
        )
    }
}