package com.district.visual;

import com.district.visual.config.ConfigManager;
import com.district.visual.gui.ClickGUI;
import com.district.visual.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DistrictVisualClient implements ClientModInitializer {

    public static final String MOD_ID = "districtvisual";
    public static final String MOD_NAME = "DistrictVisual";
    public static final String VERSION = "1.0.0";

    private static DistrictVisualClient instance;
    private ModuleManager moduleManager;
    private ConfigManager configManager;
    private ClickGUI clickGUI;

    public static KeyBinding openGuiKey;
    public static KeyBinding toggleFullbrightKey;

    @Override
    public void onInitializeClient() {
        instance = this;

        configManager = new ConfigManager();
        moduleManager = new ModuleManager();
        clickGUI = new ClickGUI();

        configManager.load();

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.districtvisual.open_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.districtvisual"
        ));

        toggleFullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.districtvisual.fullbright",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F,
            "category.districtvisual"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(clickGUI);
                }
            }
            moduleManager.onTick(client);
        });

        System.out.println("[DistrictVisual] Loaded successfully! Version " + VERSION);
    }

    public static DistrictVisualClient getInstance() { return instance; }
    public ModuleManager getModuleManager() { return moduleManager; }
    public ConfigManager getConfigManager() { return configManager; }
    public ClickGUI getClickGUI() { return clickGUI; }
}
