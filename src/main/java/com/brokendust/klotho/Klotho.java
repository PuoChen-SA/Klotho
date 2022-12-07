package com.brokendust.klotho;

import com.brokendust.klotho.network.NetworkHandler;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("klotho")
public class Klotho
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static String modId= "klotho";

    public static Logger logger;

    public static NetworkHandler networkHandler;

    public Klotho() {
        networkHandler = new NetworkHandler();
    }

    public static void sendLogger(String message) {
        LOGGER.info(message);
    }
}
