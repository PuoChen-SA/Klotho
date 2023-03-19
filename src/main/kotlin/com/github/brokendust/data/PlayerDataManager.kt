package com.github.brokendust.data

import org.bukkit.entity.Player
import taboolib.common.platform.function.getDataFolder
import taboolib.module.configuration.Configuration
import java.io.File
import java.util.*

object PlayerDataManager {
    private val playerDataMap: MutableMap<UUID, PlayerData> = mutableMapOf()

    fun getPlayerData(player: Player): PlayerData {
        val uuid = player.uniqueId
        return playerDataMap.computeIfAbsent(uuid) {
            loadPlayerData(uuid)
        }
    }

    private fun loadPlayerData(uuid: UUID): PlayerData {
        val file = File(getDataFolder(), "players/$uuid.yml")
        if (!file.exists()) {
            return PlayerData(uuid , "")
        }
        val config = Configuration.loadFromFile(file)
        val jobName = config.getString("profession.name") ?: ""
        return PlayerData(uuid, jobName)
    }

    fun savePlayerData(playerData: PlayerData) {
        val file = File(getDataFolder(), "players/${playerData.uuid}.yml")
        if (!file.exists()) {
            file.createNewFile()
        }
        val config = Configuration.loadFromFile(file)
        config["profession.name"] = playerData.profession
        config.saveToFile(file)
        playerDataMap[playerData.uuid] = playerData
    }
}