package dev.interfiber.karpet.server.init

import com.moandjiezana.toml.Toml
import mu.KotlinLogging
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.event.server.ServerListPingEvent
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import javax.imageio.ImageIO


private val logger = KotlinLogging.logger {}


class ServerListView {
    fun serverPingHandler(pingEvent: ServerListPingEvent, config: Toml?){
        val serverConfig = config?.getTable("server");

        val serverDesc = serverConfig?.getString("desc");
        val serverShowPlayers = serverConfig?.getBoolean("show-player-names");
        val serverMaxPlayers = serverConfig?.getLong("max-players");

        val responseData = pingEvent.responseData;
        responseData.description = serverDesc?.let { Component.text(it) }
        if (serverMaxPlayers != null) {
            responseData.maxPlayer = serverMaxPlayers.toInt()
        }
        if (serverShowPlayers == true){
            println(MinecraftServer.getConnectionManager().onlinePlayers)
            responseData.addEntries(MinecraftServer.getConnectionManager().onlinePlayers);
        }
    }
}