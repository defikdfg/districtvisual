package com.district.visual.gui;

import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

public class MacTitleScreen extends Screen {

    private static final int DOCK_H = 64;
    private static final int ICON_SIZE = 48;
    private static final int PADDING = 10;
    private long startTime;

    public MacTitleScreen() {
        super(Text.literal("DistrictVisual"));
    }

    @Override
    protected void init() {
        startTime = Util.getMeasuringTimeMs();
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        int w = this.width;
        int h = this.height;

        ctx.fill(0, 0, w, h, 0xFF08080F);

        long elapsed = Util.getMeasuringTimeMs() - startTime;
        float t = (float)(elapsed % 8000) / 8000f;
        ctx.fillGradient(0, 0, w, h / 2,
            ThemeModule.withAlpha(ThemeModule.getGradientColor(t), 55), 0x00000000);
        ctx.fillGradient(0, h / 2, w, h,
            0x00000000, ThemeModule.withAlpha(ThemeModule.getGradientColor((t + 0.5f) % 1f), 35));

        if (textRenderer != null) {
            ctx.getMatrices().push();
            ctx.getMatrices().scale(3f, 3f, 1f);
            String logo = "districtvisual";
            int lw = textRenderer.getWidth(logo);
            ctx.drawTextWithShadow(textRenderer, logo, (w / 2 - lw * 3 / 2) / 3, (h / 3 - 12) / 3, ThemeModule.primaryColor);
            ctx.getMatrices().pop();
            String sub = "1.21.4  \u2022  Visual Mod";
            int sw2 = textRenderer.getWidth(sub);
            ctx.drawTextWithShadow(textRenderer, sub, w / 2 - sw2 / 2, h / 3 + 22, 0xFF777777);
        }

        // Dock
        int dockW = 5 * (ICON_SIZE + PADDING) + PADDING;
        int dockX = w / 2 - dockW / 2;
        int dockY = h - DOCK_H - 20;

        ctx.fill(dockX - 10, dockY - 10, dockX + dockW + 10, dockY + DOCK_H + 10, 0xCC0E0E18);
        ctx.fill(dockX - 10, dockY - 10, dockX + dockW + 10, dockY - 9, 0x44FFFFFF);

        String[] icons = {"\u2694", "\uD83C\uDF10", "\u2699", "\uD83D\uDCE6", "\u2716"};
        String[] labels = {"Singleplayer", "Multiplayer", "Options", "Mods", "Quit"};
        int[] colors = {0xFF7B61FF, 0xFF00D4FF, 0xFF888888, 0xFF44FF88, 0xFFFF4444};

        for (int i = 0; i < 5; i++) {
            int ix = dockX + PADDING + i * (ICON_SIZE + PADDING);
            int iy = dockY + (DOCK_H - ICON_SIZE) / 2;
            boolean hov = mouseX >= ix && mouseX <= ix + ICON_SIZE && mouseY >= iy && mouseY <= iy + ICON_SIZE;

            ctx.fill(ix, iy, ix + ICON_SIZE, iy + ICON_SIZE, hov ? ThemeModule.withAlpha(colors[i], 70) : 0x44181818);
            ctx.fill(ix, iy, ix + ICON_SIZE, iy + 2, colors[i]);

            if (textRenderer != null) {
                ctx.getMatrices().push();
                ctx.getMatrices().scale(1.5f, 1.5f, 1f);
                int tw = textRenderer.getWidth(icons[i]);
                ctx.drawTextWithShadow(textRenderer, icons[i],
                    (int)((ix + ICON_SIZE / 2 - tw / 2) / 1.5f),
                    (int)((iy + ICON_SIZE / 2 - 4) / 1.5f), colors[i]);
                ctx.getMatrices().pop();

                if (hov) {
                    int lw = textRenderer.getWidth(labels[i]);
                    ctx.fill(ix + ICON_SIZE/2 - lw/2 - 3, iy - 18, ix + ICON_SIZE/2 + lw/2 + 3, iy - 4, 0xCC000000);
                    ctx.drawTextWithShadow(textRenderer, labels[i], ix + ICON_SIZE/2 - lw/2, iy - 16, 0xFFFFFFFF);
                }
                ctx.fill(ix + ICON_SIZE/2 - 2, iy + ICON_SIZE + 4, ix + ICON_SIZE/2 + 2, iy + ICON_SIZE + 8, colors[i]);
            }
        }

        if (textRenderer != null) {
            ctx.drawTextWithShadow(textRenderer, "v1.0.0 \u2022 Fabric 1.21.4", 5, h - 12, 0xFF333333);
        }
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        int dockW = 5 * (ICON_SIZE + PADDING) + PADDING;
        int dockX = width / 2 - dockW / 2;
        int dockY = height - DOCK_H - 20;

        for (int i = 0; i < 5; i++) {
            int ix = dockX + PADDING + i * (ICON_SIZE + PADDING);
            int iy = dockY + (DOCK_H - ICON_SIZE) / 2;
            if (mx >= ix && mx <= ix + ICON_SIZE && my >= iy && my <= iy + ICON_SIZE) {
                handleClick(i);
                return true;
            }
        }
        return super.mouseClicked(mx, my, button);
    }

    private void handleClick(int i) {
        MinecraftClient mc = MinecraftClient.getInstance();
        switch (i) {
            case 0 -> mc.setScreen(new SelectWorldScreen(this));
            case 1 -> mc.setScreen(new MultiplayerScreen(this));
            case 2 -> mc.setScreen(new net.minecraft.client.gui.screen.option.OptionsScreen(this, mc.options));
            case 3 -> mc.setScreen(new ClickGUI());
            case 4 -> mc.scheduleStop();
        }
    }

    @Override
    public boolean shouldCloseOnEsc() { return false; }
}
