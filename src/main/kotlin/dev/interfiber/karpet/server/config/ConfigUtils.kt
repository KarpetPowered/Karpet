package dev.interfiber.karpet.server.config

import mu.KotlinLogging
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID


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

    fun generateServerUUID(): String {
        val uuid = UUID.randomUUID();
        return uuid.toString();
    }

    fun getServerUUID(): String {
        val uuidFile = Files.readAllLines(Paths.get(".karpetuuid"), StandardCharsets.UTF_8);
        return uuidFile.joinToString("")
    }
}