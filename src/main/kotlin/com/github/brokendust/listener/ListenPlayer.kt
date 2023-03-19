package com.github.brokendust.listener

import com.github.brokendust.data.PlayerDataManager
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object ListenPlayer {
    @SubscribeEvent
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val playerData = PlayerDataManager.getPlayerData(player)
        // 根据玩家的数据，设置职业和技能
        // ...
    }
    @SubscribeEvent
    fun onQuit(event: PlayerQuitEvent){
        val player = event.player
        val playerData = PlayerDataManager.getPlayerData(player)
        // 保存玩家数据
        PlayerDataManager.savePlayerData(playerData)
    }
}