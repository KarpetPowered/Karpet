package dev.interfiber.karpet.server.config

import mu.KotlinLogging
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * Config utils for karpet.toml
 * @author Interfiber
 */
class ConfigUtils {
    /**
     * Create the karpet.toml file
     * @author Interfiber
     */
    fun createConfig(): String {
        logger.info("Creating config file...")
        logger.debug("Copying config file from resources")
        val configResource = javaClass.classLoader.getResource("karpet.toml")
        if (configResource == null) {
            logger.error("Failed to copy file from resources, result was null!")
            throw IllegalArgumentException("Resource file not found!")
        } else {
            val configFile = configResource.readText()
            logger.info("Writing config file")
            File("karpet.toml").writeText(configFile)
            logger.info("Config file created")
            return configFile
        }
    }
    /**
     * Return the raw text of the config file
     * @author Interfiber
     */
    fun readConfig(): String {
        val configFile = File("karpet.toml")
        return configFile.readText()
    }
    /**
     * Generate a UUID for the server
     * This is used to report Bstats metrics
     * @author Interfiber
     */
    fun generateServerUUID(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }
    /**
     * Return the server UUID
     * @author Interfiber
     */

    fun getServerUUID(): String {
        val uuidFile = Files.readAllLines(Paths.get(".karpetuuid"), StandardCharsets.UTF_8)
        return uuidFile.joinToString("")
    }
}
