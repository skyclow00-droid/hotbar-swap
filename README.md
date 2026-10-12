# Hotbar Swap – Instant Item Switching (Minecraft 26.3 Fabric)

Mod Fabric per Minecraft Java Edition **26.3** che permette di scambiare all'istante l'oggetto della hotbar correntemente selezionato con uno dei 3 oggetti disposti nella rispettiva colonna dell'inventario, senza bisogno di aprire la schermata dell'inventario.

---

## 🚀 Caratteristiche

1. **Scambio Reale e Sicuro**:
   - Gli oggetti vengono scambiati fisicamente tra gli slot dell'inventario (`PlayerInventory`).
   - Nessuna duplicazione, perdita di dati, alterazione di stack, NBT o componenti.
2. **Ciclo a 3 Livelli su 9 Colonne Indipendenti**:
   - Ciascuno dei 9 slot della hotbar ha una memoria di stato separata.
   - Pressione 1: scambio con il 1° slot sopra la hotbar.
   - Pressione 2: scambio con il 2° slot della colonna.
   - Pressione 3: scambio con il 3° slot della colonna.
   - Pressione 4: il ciclo ricomincia dal primo slot.
   - Le altre 8 colonne rimangono intatte.
3. **Integrazione Controlli (Tasto F predefinito)**:
   - Configurato di default sul tasto **F**.
   - Completamente rimappabile dal menu *Opzioni -> Comandi / Controlli* di Minecraft.
4. **Architettura Client/Server Robusta**:
   - Il client cattura l'input quando la visuale è libera (nessun menu aperto).
   - Invia un payload di rete al server (`SwapHotbarPayload`).
   - Il server valida lo slot ed esegue lo scambio atomico, prevenendo disallineamenti e "ghost items".

---

## 📁 Struttura del Progetto

```
hotbar-swap/
├── build.gradle
├── gradle.properties
├── settings.gradle
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── hotbarswap/
        │           ├── HotbarSwapMod.java             # Entrypoint server/comune, registrazione rete
        │           ├── client/
        │           │   └── HotbarSwapClientMod.java   # Registrazione KeyBinding e invio input
        │           ├── logic/
        │           │   └── HotbarSwapManager.java     # Algoritmo ciclico colonne & scambio inventario
        │           └── network/
        │               └── SwapHotbarPayload.java     # Pacchetto di sincronizzazione C2S
        └── resources/
            ├── fabric.mod.json                        # Metadati della mod Fabric
            ├── hotbarswap.mixins.json                 # Configurazione Mixin
            └── assets/
                └── hotbarswap/
                    └── lang/
                        ├── en_us.json                 # Localizzazione inglese
                        └── it_it.json                 # Localizzazione italiana
```

---

## 🛠️ Come Compilare e Installare

### Requisiti
- **Java 25+** (ad es. OpenJDK / Eclipse Temurin 25)
- **Minecraft 26.3** con **Fabric Loader** (0.19.5 o successivi) e **Fabric API** (0.162.0+26.3)

### Compilazione
Nella cartella del progetto, esegui:

```bash
gradle build
```
oppure, se è presente il Gradle wrapper:
```bash
./gradlew build
```

Il file JAR compilato sarà disponibile in:
`build/libs/hotbar-swap-1.0.0.jar`

Copia il file JAR nella cartella `.minecraft/mods/` per iniziare a giocare!
