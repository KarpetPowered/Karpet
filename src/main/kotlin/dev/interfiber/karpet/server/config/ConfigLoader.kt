package dev.interfiber.karpet.server.config

import com.moandjiezana.toml.Toml


class ConfigLoader {
    // We only parse the config once, at the server start
    fun getConfig(configData: String): Toml? {
        return Toml().read(configData)
    }
}