package com.github.brokendust.data

import com.github.brokendust.profession.Profession
import org.bukkit.entity.Player
import taboolib.common.platform.function.getDataFolder
import taboolib.module.configuration.Configuration
import taboolib.platform.util.sendLang
import java.io.File
import java.util.UUID

data class PlayerData(
    val uuid: UUID,
    var profession: String
) {

    fun selectJob(player: Player, profession: Profession) {
        val playerData = PlayerData(player.uniqueId, profession.name)
        savePlayerData(playerData)
        // 发送成功消息给玩家
        player.sendLang("profession-select-success" , profession.name)
    }

    private fun savePlayerData(playerData: PlayerData) {
        val file = File(getDataFolder() , "players/${playerData.uuid}.yml")
        if (!file.exists()) {
            file.createNewFile()
        }
        val config = Configuration.loadFromFile(file)
        config["profession.name"] = playerData.profession
        config.saveToFile(file)
    }

}