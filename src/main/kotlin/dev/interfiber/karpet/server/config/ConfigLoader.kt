package dev.interfiber.karpet.server.config

import com.moandjiezana.toml.Toml

/**
 * Loads the karpet config file
 * @author Interfiber
 */
class ConfigLoader {
    // We only parse the config once, at the server start
    /**
     * Returns the karpet.toml as a parsed config
     * @return Toml
     * @author Interfiber
     */
    fun getConfig(configData: String): Toml? {
        return Toml().read(configData)
    }
}