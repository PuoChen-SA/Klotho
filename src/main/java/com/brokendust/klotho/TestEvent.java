package com.brokendust.klotho;

import com.brokendust.klotho.gui.TestGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TestEvent {
    @SubscribeEvent
    public static void pickUpItemEvent(PlayerEvent.ItemPickupEvent event){
        Minecraft.getInstance().setScreen(new TestGui(new TranslationTextComponent(Klotho.modId + ".test")));
    }
}
