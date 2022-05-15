import dev.interfiber.karpet.server.init.ServerBootstrap
import mu.KotlinLogging
private val logger = KotlinLogging.logger {}

fun main() {
    logger.info("Karpet version 1.0-SNAPSHOT");
    logger.info("Starting karpet server...");
    ServerBootstrap().startServer();
}