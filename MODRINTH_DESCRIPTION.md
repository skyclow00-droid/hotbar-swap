# Hotbar Swap

Hotbar Swap is a quality-of-life mod designed to streamline inventory management in Minecraft. It introduces a fast, reliable method to rotate through alternative items for each hotbar slot directly during gameplay, eliminating the need to constantly pause and open the inventory screen during combat, building, or mining.

## Overview

Managing hotbar space in Minecraft often interrupts the flow of gameplay. Whether carrying multiple tools, rotating building blocks, or accessing emergency consumables like food and potions, players frequently have to stop and rearrange their hotbar manually.

Hotbar Swap solves this problem by treating each hotbar slot as the base of a vertical column. In vanilla Minecraft, the main inventory contains three rows of nine slots located directly above the nine hotbar slots. Hotbar Swap turns each vertical column into an accessible item rotation pool that can be cycled with a single keypress.

## How It Works

Each of the nine hotbar slots operates independently with its own three-slot column:

1. Select any hotbar slot (1 to 9).
2. Press the configured swap key (default: F).
3. The item currently held in your hotbar is swapped with the first inventory slot directly above it.
4. Press the key again to swap with the second slot in the vertical column.
5. Press the key a third time to swap with the third (topmost) slot in the column.
6. A fourth press returns the cycle back to the first slot.

All eight other columns remain completely untouched. Each column remembers its current position in the cycle, allowing players to rotate items across different slots without resetting or conflicting with other columns.

## Practical Use Cases

* Building: keep different block variants, stairs, or slabs stored in the same column and cycle through them while building without opening menus.
* Mining and Gathering: store spare pickaxes, torches, and ladders in the same column to replace damaged tools or place lights instantly.
* Combat and Exploration: quickly swap between weapons, utility items, shields, water buckets, or ender pearls during intense encounters.

## Technical Details and Safety

* Genuine Inventory Movement: items are moved atomically between actual inventory slots. The mod does not use virtual storage, ghost items, or temporary buffers, ensuring no items are duplicated or lost.
* Stack and NBT Compatibility: works cleanly with all items, stacked items, enchanted gear, durability states, and custom item components.
* Configurable Controls: the swap action is registered under the standard Minecraft Controls menu and can be assigned to any keyboard or mouse button.
* Sound Feedback: includes a subtle audio cue on each swap to provide immediate feedback during action.

## Requirements

* Minecraft 26.3
* Fabric Loader 0.19.0 or newer
* Fabric API
