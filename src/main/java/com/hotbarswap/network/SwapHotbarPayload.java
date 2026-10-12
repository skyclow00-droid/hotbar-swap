package com.hotbarswap.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Payload inviato dal Client al Server per richiedere lo scambio della colonna hotbar selezionata.
 */
public record SwapHotbarPayload(int hotbarSlot) implements CustomPacketPayload {
    public static final Type<SwapHotbarPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("hotbarswap", "swap_hotbar"));
    public static final StreamCodec<ByteBuf, SwapHotbarPayload> CODEC = ByteBufCodecs.VAR_INT.map(
            SwapHotbarPayload::new,
            SwapHotbarPayload::hotbarSlot
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
