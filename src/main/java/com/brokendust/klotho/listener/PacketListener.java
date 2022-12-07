package com.brokendust.klotho.listener;

import com.brokendust.klotho.Klotho;
import com.brokendust.klotho.gui.TestGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class PacketListener {
    public static void onReceiveServerMessage(String receivedString) {
        Klotho.sendLogger(receivedString);
        if (receivedString.equalsIgnoreCase("opengui")) {
            Minecraft.getInstance().setScreen(new TestGui(new TranslationTextComponent(Klotho.modId + ".test")));
        }
    }
}
