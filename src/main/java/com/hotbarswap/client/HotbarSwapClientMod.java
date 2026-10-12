package com.hotbarswap.client;

import com.hotbarswap.network.SwapHotbarPayload;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.KeyMapping;

@Environment(EnvType.CLIENT)
public class HotbarSwapClientMod implements ClientModInitializer {

    private static KeyMapping swapKeyMapping;

    @Override
    public void onInitializeClient() {
        // Registrazione del KeyMapping personalizzabile (Tasto F predefinito con costante InputConstants)
        swapKeyMapping = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.hotbarswap.swap_key",
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_F,
                "key.categories.inventory"
        ));

        // Gestione della pressione durante il ciclo di gioco
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (swapKeyMapping.consumeClick()) {
                if (client.player != null && client.screen == null) {
                    int selectedSlot = client.player.getInventory().selected;
                    // Invia il pacchetto autoritativo al server
                    ClientPlayNetworking.send(SwapHotbarPayload.ID, PacketByteBufs.create().writeVarInt(selectedSlot));
                }
            }
        });
    }
}
