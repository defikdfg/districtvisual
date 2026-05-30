package com.district.visual.module;

import com.district.visual.module.combat.*;
import com.district.visual.module.hud.*;
import com.district.visual.module.visual.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();

    // Combat
    public final HitColorModule hitColor;
    public final HitSoundModule hitSound;
    public final HitParticlesModule hitParticles;
    public final DamageNumbersModule damageNumbers;
    public final TargetHudModule targetHud;
    public final HitboxModule hitbox;
    public final GhostCrystalModule ghostCrystal;
    public final CrosshairModule crosshair;
    public final CustomCursorModule customCursor;
    public final AutoSwapModule autoSwap;
    public final TotemAnimationModule totemAnimation;

    // HUD
    public final InventoryHudModule inventoryHud;
    public final KeystrokesModule keystrokes;
    public final DynamicIslandModule dynamicIsland;
    public final LiquidGlassModule liquidGlass;
    public final MinimapModule minimap;
    public final ArmorHudModule armorHud;
    public final SpeedHudModule speedHud;
    public final SessionStatsModule sessionStats;
    public final FpsPingModule fpsPing;

    // Visual
    public final HandEffectModule handEffect;
    public final SwingAnimationModule swingAnimation;
    public final ViewModelModule viewModel;
    public final AngelWingsModule angelWings;
    public final MechvibesModule mechvibes;
    public final CustomFogModule customFog;
    public final FullbrightModule fullbright;
    public final AspectRatioModule aspectRatio;
    public final ThemeModule theme;
    public final TrailEffectModule trailEffect;
    public final NightVisionEffectModule nightVision;

    public ModuleManager() {
        // Combat
        hitColor = register(new HitColorModule());
        hitSound = register(new HitSoundModule());
        hitParticles = register(new HitParticlesModule());
        damageNumbers = register(new DamageNumbersModule());
        targetHud = register(new TargetHudModule());
        hitbox = register(new HitboxModule());
        ghostCrystal = register(new GhostCrystalModule());
        crosshair = register(new CrosshairModule());
        customCursor = register(new CustomCursorModule());
        autoSwap = register(new AutoSwapModule());
        totemAnimation = register(new TotemAnimationModule());

        // HUD
        inventoryHud = register(new InventoryHudModule());
        keystrokes = register(new KeystrokesModule());
        dynamicIsland = register(new DynamicIslandModule());
        liquidGlass = register(new LiquidGlassModule());
        minimap = register(new MinimapModule());
        armorHud = register(new ArmorHudModule());
        speedHud = register(new SpeedHudModule());
        sessionStats = register(new SessionStatsModule());
        fpsPing = register(new FpsPingModule());

        // Visual
        handEffect = register(new HandEffectModule());
        swingAnimation = register(new SwingAnimationModule());
        viewModel = register(new ViewModelModule());
        angelWings = register(new AngelWingsModule());
        mechvibes = register(new MechvibesModule());
        customFog = register(new CustomFogModule());
        fullbright = register(new FullbrightModule());
        aspectRatio = register(new AspectRatioModule());
        theme = register(new ThemeModule());
        trailEffect = register(new TrailEffectModule());
        nightVision = register(new NightVisionEffectModule());

        // Enable defaults
        theme.setEnabled(true);
        dynamicIsland.setEnabled(true);
        keystrokes.setEnabled(true);
        fpsPing.setEnabled(true);
    }

    private <T extends Module> T register(T module) {
        modules.add(module);
        return module;
    }

    public void onTick(MinecraftClient client) {
        for (Module m : modules) {
            if (m.isEnabled()) m.onTick(client);
        }
    }

    public void onRenderHud(DrawContext ctx, float delta) {
        for (Module m : modules) {
            if (m.isEnabled()) m.onRenderHud(ctx, delta);
        }
    }

    public List<Module> getModules() { return modules; }

    public List<Module> getByCategory(Module.Category cat) {
        List<Module> result = new ArrayList<>();
        for (Module m : modules) {
            if (m.getCategory() == cat) result.add(m);
        }
        return result;
    }

    public Module getByName(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }
}
