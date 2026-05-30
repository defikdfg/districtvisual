package com.district.visual.config;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.Module;
import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.*;

public class ConfigManager {
    private final Path configDir;
    private final Path configFile;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {
        configDir = FabricLoader.getInstance().getConfigDir().resolve("districtvisual");
        configFile = configDir.resolve("modules.json");
    }

    public void save() {
        try {
            Files.createDirectories(configDir);
            JsonObject root = new JsonObject();
            for (Module m : DistrictVisualClient.getInstance().getModuleManager().getModules()) {
                root.addProperty(m.getName(), m.isEnabled());
            }
            Files.writeString(configFile, gson.toJson(root));
        } catch (IOException e) {
            System.err.println("[DistrictVisual] Failed to save config: " + e.getMessage());
        }
    }

    public void load() {
        if (!Files.exists(configFile)) return;
        try {
            String json = Files.readString(configFile);
            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            for (Module m : DistrictVisualClient.getInstance().getModuleManager().getModules()) {
                if (root.has(m.getName())) {
                    boolean enabled = root.get(m.getName()).getAsBoolean();
                    m.setEnabled(enabled);
                }
            }
        } catch (Exception e) {
            System.err.println("[DistrictVisual] Failed to load config: " + e.getMessage());
        }
    }
}
