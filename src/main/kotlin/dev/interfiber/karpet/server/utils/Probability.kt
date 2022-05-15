package dev.interfiber.karpet.server.utils

import java.util.*

object Probability {
    fun calculate(Percent: Double): Boolean {
        val r = Random()
        val rng = r.nextInt(100)
        return rng < Percent
    }
}