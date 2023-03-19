package com.github.brokendust

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.module.configuration.Config
import taboolib.module.lang.Language
import taboolib.platform.BukkitPlugin

object Klotho : Plugin() {

    @Config
    lateinit var config: Config

    val plugin by lazy {
        BukkitPlugin.getPluginInstance()
    }

    override fun onEnable() {
        Language.default = "zh_CN"
    }

}