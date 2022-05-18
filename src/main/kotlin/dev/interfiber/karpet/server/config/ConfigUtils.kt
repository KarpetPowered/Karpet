package dev.interfiber.karpet.server.config

import mu.KotlinLogging
import java.io.File


private val logger = KotlinLogging.logger {}

class ConfigUtils {

    fun createConfig(): String {
        logger.info("Creating config file...")
        logger.debug("Copying config file from resources")
        val configResource = javaClass.classLoader.getResource("karpet.toml")
        if (configResource == null){
            logger.error("Failed to copy file from resources, result was null!")
            throw IllegalArgumentException("Resource file not found!")
        } else {
            val configFile = configResource.readText();
            logger.info("Writing config file");
            File("karpet.toml").writeText(configFile)
            logger.info("Config file created")
            return configFile;
        }

    }

    fun readConfig(): String {
        val configFile = File("karpet.toml")
        return configFile.readText()
    }
}