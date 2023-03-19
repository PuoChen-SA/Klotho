package com.github.brokendust.profession

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import taboolib.library.xseries.getItemStack
import taboolib.module.configuration.Configuration

class Profession(
    val name: String,
    val icon: ItemStack
) {

    constructor(config: Configuration) : this(
        config.getString("name") ?: "Unknown",
        config.getItemStack("icon") ?: ItemStack(Material.AIR)
    )



}