package com.district.visual.module.hud;

import com.district.visual.module.Module;
import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DynamicIslandModule extends Module {

    public static class Notification {
        public String title;
        public String message;
        public int icon; // color indicator
        public int lifetime;
        public int age = 0;
        public float expand = 0f;

        public Notification(String title, String message, int icon) {
            this.title = title; this.message = message; this.icon = icon;
            this.lifetime = 80;
        }
    }

    private static final List<Notification> active = new ArrayList<>();
    private static final Queue<Notification> queue = new ArrayDeque<>();

    public DynamicIslandModule() {
        super("DynamicIsland", "iPhone-style Dynamic Island notifications", Category.HUD);
        enabled = true;
    }

    public static void push(String title, String msg, int iconColor) {
        queue.add(new Notification(title, msg, iconColor));
    }

    @Override
    public void onTick(MinecraftClient client) {
        if (!queue.isEmpty() && active.size() < 2) {
            active.add(queue.poll());
        }
        active.removeIf(n -> {
            n.age++;
            n.expand = n.age < 5 ? n.age / 5f : n.age > n.lifetime - 10 ? (n.lifetime - n.age) / 10f : 1f;
            return n.age > n.lifetime;
        });
    }

    @Override
    public void onRenderHud(DrawContext ctx, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        int sw = mc.getWindow().getScaledWidth();
        int startX = sw / 2;
        int y = 8;

        for (int i = 0; i < active.size(); i++) {
            Notification n = active.get(i);
            int w = (int)(200 * n.expand);
            int h = (int)(40 * Math.min(1f, n.expand * 2));
            int x = startX - w / 2;

            // Pill background
            ctx.fill(x, y, x + w, y + h, 0xEE0A0A0A);
            // Top accent bar
            ctx.fill(x, y, x + w, y + 2, n.icon);
            // Icon dot
            ctx.fill(x + 6, y + (h - 8) / 2, x + 14, y + (h - 8) / 2 + 8, n.icon);

            if (n.expand > 0.7f && mc.textRenderer != null) {
                ctx.drawTextWithShadow(mc.textRenderer, n.title, x + 20, y + 5, 0xFFFFFFFF);
                ctx.drawTextWithShadow(mc.textRenderer, n.message, x + 20, y + 16, 0xFFAAAAAA);
            }
            y += h + 4;
        }
    }
}
