package com.hotbarswap.logic;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Gestisce la logica di scambio ciclico tra lo slot della hotbar
 * e i 3 slot verticali corrispondenti nell'inventario principale.
 *
 * Mappatura colonne inventario vanilla:
 * Hotbar: slot 0..8
 * Riga 1 (sopra la hotbar): slot = hotbarSlot + 27
 * Riga 2: slot = hotbarSlot + 18
 * Riga 3 (in alto): slot = hotbarSlot + 9
 */
public class HotbarSwapManager {

    // Memorizza lo stato del ciclo (step 0, 1, 2) per ciascuna delle 9 colonne di ogni giocatore
    private static final Map<UUID, int[]> PLAYER_COLUMN_STEPS = new HashMap<>();

    /**
     * Esegue lo scambio ciclico per il giocatore e lo slot specificato.
     *
     * @param player Il giocatore server che esegue l'azione
     * @param hotbarSlot L'indice dello slot hotbar selezionato (0 - 8)
     */
    public static void executeSwap(ServerPlayer player, int hotbarSlot) {
        if (hotbarSlot < 0 || hotbarSlot > 8) {
            return;
        }

        Inventory inventory = player.getInventory();
        UUID playerId = player.getUUID();

        int[] steps = PLAYER_COLUMN_STEPS.computeIfAbsent(playerId, k -> new int[9]);
        int currentStep = steps[hotbarSlot];

        // Definizione dei 3 slot verticali sopra lo slot hotbar:
        int[] verticalSlots = new int[] {
                hotbarSlot + 27, // 1° slot sopra hotbar
                hotbarSlot + 18, // 2° slot sopra hotbar
                hotbarSlot + 9   // 3° slot sopra hotbar
        };

        int targetInventorySlot = verticalSlots[currentStep];

        // Scambio atomico e reale tra hotbarSlot e targetInventorySlot
        ItemStack hotbarStack = inventory.getItem(hotbarSlot);
        ItemStack targetStack = inventory.getItem(targetInventorySlot);

        inventory.setItem(hotbarSlot, targetStack);
        inventory.setItem(targetInventorySlot, hotbarStack);

        // Aggiorna lo stato dell'inventario e invia la sincronizzazione al client
        inventory.setChanged();
        if (player.containerMenu != null) {
            player.containerMenu.broadcastChanges();
        }

        // Avanza al passo successivo (0 -> 1 -> 2 -> 0)
        steps[hotbarSlot] = (currentStep + 1) % 3;

        // Feedback sonoro per il giocatore
        player.level().playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                SoundEvents.UI_BUTTON_CLICK.value(),
                SoundSource.PLAYERS,
                0.35F,
                1.4F + (steps[hotbarSlot] * 0.15F)
        );
    }

    /**
     * Rimuove la memoria degli step quando un giocatore si disconnette.
     */
    public static void clearPlayerData(UUID playerId) {
        PLAYER_COLUMN_STEPS.remove(playerId);
    }
}
