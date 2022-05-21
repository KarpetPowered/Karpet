package dev.interfiber.karpet.server.metrics
import com.moandjiezana.toml.Toml
import dev.interfiber.karpet.server.config.ConfigUtils
import mu.KotlinLogging
import org.bstats.MetricsBase
import org.bstats.charts.SimplePie
import org.bstats.json.JsonObjectBuilder

private val logger = KotlinLogging.logger {}

class Tracker {
    private val pluginID = 15250

    private fun appendPlatformInfo(builder: JsonObjectBuilder) {
        builder.appendField("osName", System.getProperty("os.name"));
        builder.appendField("osArch", System.getProperty("os.arch"));
        builder.appendField("osVersion", System.getProperty("os.version"));
        builder.appendField("coreCount", Runtime.getRuntime().availableProcessors());
    }

    fun reportInfo(serverConfig: Toml) {
        val serverUUID = ConfigUtils().getServerUUID()
        val metrics = MetricsBase(
            "server-implementation",
            serverUUID,
            this.pluginID,
            true,
            this::appendPlatformInfo,
            { _: JsonObjectBuilder? -> },
            null,
            { true },
            logger::warn,
            logger::info,
            true,
            true,
            true
        )
        metrics.addCustomChart(SimplePie("onlineMode") {
            if (serverConfig.getBoolean("online-mode") == true) {
                return@SimplePie "online";
            } else {
                return@SimplePie "offline";
            }
        });
    }
}