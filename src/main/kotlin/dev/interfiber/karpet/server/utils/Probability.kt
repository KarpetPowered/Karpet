package dev.interfiber.karpet.server.utils

import java.util.Random

/**
 * Calculate probability
 * @author Interfiber
 */
object Probability {
    /**
     * Calculate a probability based on a percent
     * @author Interfiber
     */
    fun calculate(Percent: Double): Boolean {
        val r = Random()
        val rng = r.nextInt(100)
        return rng < Percent
    }
}
