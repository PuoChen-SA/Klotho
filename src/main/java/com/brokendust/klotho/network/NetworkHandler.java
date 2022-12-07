package com.brokendust.klotho.network;

import com.brokendust.klotho.listener.PacketListener;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class NetworkHandler {
    private static int IDX = 100;

    public SimpleChannel simpleChannel;

    public NetworkHandler(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(FMLClientSetupEvent event){
        this.simpleChannel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation("klotho" , "klothogui"))
                .networkProtocolVersion(() -> "http.protocol.version")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        this.simpleChannel.registerMessage(100, String.class, this::enc, this::dec, this::proc);
    }

    private void enc(String str, PacketBuffer buffer) {
        buffer.writeBytes(str.getBytes(StandardCharsets.UTF_8));
    }

    private String dec(PacketBuffer buffer) {
        return buffer.toString(StandardCharsets.UTF_8);
    }

    private void proc(String str, Supplier<NetworkEvent.Context> supplier) {
        String message = GZIPUtils.uncompress(str);
        PacketListener.onReceiveServerMessage(message);
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
    }

    public void send(String type, String message) {
        try {
            this.simpleChannel.sendToServer(type + " : " + message);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
    }

}
