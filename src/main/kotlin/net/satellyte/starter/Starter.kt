package net.satellyte.starter

import net.axay.kspigot.main.KSpigot

class Starter : KSpigot() {
    companion object {
        lateinit var INSTANCE: Starter
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() {
        logger.info("Hello from $name!")
    }

    override fun shutdown() { }
}