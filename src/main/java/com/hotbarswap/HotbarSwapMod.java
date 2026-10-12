package com.hotbarswap;

import com.hotbarswap.logic.HotbarSwapManager;
import com.hotbarswap.network.SwapHotbarPayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotbarSwapMod implements ModInitializer {
    public static final String MOD_ID = "hotbarswap";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Inizializzazione di Hotbar Swap (Minecraft 26.3)...");

        // Ricezione del pacchetto autoritativo sul server
        ServerPlayNetworking.registerGlobalReceiver(SwapHotbarPayload.ID, (server, player, handler, buf, responseSender) -> {
            int hotbarSlot = buf.readVarInt();
            server.execute(() -> {
                HotbarSwapManager.executeSwap(player, hotbarSlot);
            });
        });

        // Pulizia dei dati del giocatore alla disconnessione
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            HotbarSwapManager.clearPlayerData(handler.player.getUUID());
        });

        LOGGER.info("Hotbar Swap inizializzato con successo!");
    }
}
