# Veilfall

A Minecraft Java Edition 1.20.1 apocalypse mod for Forge 47.4.18. A hostile corruption spreads through the world, twisting terrain and bringing dangerous creatures with it.

## Implemented prototype features

- Persistent world corruption meter from 0% to 100%.
- HUD corruption bar.
- Corrupted Ground block that can spread to nearby dirt and grass through random ticks.
- Purification Shard: right-click corrupted ground to turn it back into dirt and reduce the corruption meter.
- Ten placeholder hostile enemy types that can spawn only on corrupted ground in overworld biomes.
- The Unmaking boss: 300 health, a damaging pulse, and minion summons below 70% health.
- Commands:
  - `/veilfall status` — view the current corruption.
  - `/veilfall set <0-100>` — set corruption (operator permission required).
  - `/veilfall clean <1-100>` — reduce the meter (operator permission required).

## Prototype notes

This is intentionally a rough first pass. The ten enemy types currently reuse the vanilla zombie model and behavior, and the corrupted ground currently uses the vanilla netherrack texture. The boss has placeholder visuals too. Custom models, textures, sounds, animations, more distinct enemy abilities, and deeper purification/world recovery mechanics will be added in later passes.

## Target environment

- Minecraft Java Edition 1.20.1
- Minecraft Forge 47.4.18
- Java 17

## Build

GitHub Actions runs the **Build Veilfall** workflow on pushes to `main`. A successful run publishes an artifact named `veilfall-forge-1.20.1` containing the mod JAR. The build must pass before a JAR should be considered ready to test.
