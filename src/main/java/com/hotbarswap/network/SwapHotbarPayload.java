package com.hotbarswap.network;

import net.minecraft.resources.ResourceLocation;

/**
 * Identificatore del pacchetto C2S (API di rete legacy, Minecraft < 1.20.5).
 */
public final class SwapHotbarPayload {
    public static final ResourceLocation ID = new ResourceLocation("hotbarswap", "swap_hotbar");

    private SwapHotbarPayload() {
    }
}
