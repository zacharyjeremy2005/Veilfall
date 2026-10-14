# Veilfall

**Veilfall** is a Minecraft Java Edition 1.20.1 apocalypse mod for Forge 47.4.18. A hostile corruption spreads through the world, twisting terrain and bringing dangerous creatures with it.

## Current development status

This repository is the starting point for the mod. The Forge project structure and automatic GitHub Actions build are in place. Gameplay systems and custom assets will be added incrementally and tested through CI.

Planned systems:
- A world corruption meter from 0% to 100%.
- Corruption that spreads through nearby blocks and regions.
- Purification mechanics that reclaim corrupted land.
- Hostile creatures that naturally spawn only in corrupted areas.
- At least 10 enemy types and a boss with distinct abilities.
- New weapons and tools.
- Custom models, textures, particles, sounds, and animations.

## Target environment

- Minecraft Java Edition 1.20.1
- Minecraft Forge 47.4.18
- Java 17

## Automatic builds

Pushes to `main` trigger the **Build Veilfall** GitHub Actions workflow. When the first build succeeds, download the `veilfall-forge-1.20.1` artifact from the workflow run.

The first playable build is not ready yet; features listed above are planned until implemented and verified.
