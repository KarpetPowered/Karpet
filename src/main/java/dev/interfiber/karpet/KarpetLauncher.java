package dev.interfiber.karpet;

import dev.interfiber.karpet.server.init.ServerBootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class KarpetLauncher {
    // Log4j
    private static final Logger LOG = LogManager.getLogger(KarpetLauncher.class);
    
    public static void main(String[] args) {
        LOG.info("Karpet version 1.0-SNAPSHOT");
        LOG.info("Starting karpet server...");
        ServerBootstrap.StartServer();
    }
}