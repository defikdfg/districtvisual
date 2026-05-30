# districtvisual

Visual mod for Minecraft 1.21.4 (Fabric), inspired by Pulse Visuals.

## Features
- macOS-style Title Screen with Dock
- HitColor, HitSound, HitParticles, DamageNumbers
- TargetHUD, Hitbox, GhostCrystal, Crosshair, CustomCursor
- AutoSwap, TotemAnimation
- KeyStrokes, InventoryHUD, DynamicIsland, LiquidGlass
- Minimap, ArmorHUD, SpeedHUD, FPS/Ping, SessionStats
- HandEffect, SwingAnimation, ViewModel, AngelWings
- Mechvibes, CustomFog, Fullbright, AspectRatio, Theme
- TrailEffect, NightVision

## Building (easiest way)

### Option A — GitHub Actions (zero setup)
1. Upload this folder to a GitHub repo
2. Go to Actions tab → "Build DistrictVisual" → Run workflow
3. Download the JAR from the Artifacts section

### Option B — Local build
Requirements: Java 21 JDK

```bash
# Windows
gradlew.bat build

# Mac / Linux
./gradlew build
```

JAR will be at: `build/libs/districtvisual-1.0.0.jar`

## Installation
1. Install [Fabric Loader](https://fabricmc.net/use/) for 1.21.4
2. Install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Drop `districtvisual-1.0.0.jar` into your `.minecraft/mods/` folder
4. Launch Minecraft 1.21.4

## Controls
- `Right Shift` — Open DistrictVisual GUI
- `F` — Toggle Fullbright
