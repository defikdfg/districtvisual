package com.district.visual.gui;

import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.text.Text;

public class MacTitleScreen extends Screen {

    public MacTitleScreen() {
        super(Text.literal("DistrictVisual"));
    }

    @Override
    protected void init() {}

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        int w = this.width;
        int h = this.height;

        ctx.fill(0, 0, w, h, 0xFF08080F);

        if (textRenderer == null) return;

        // Logo
        ctx.drawTextWithShadow(textRenderer, "districtvisual", w/2 - 50, h/3, 0xFF7B61FF);
        ctx.drawTextWithShadow(textRenderer, "1.21.4  Fabric Visual Mod", w/2 - 60, h/3 + 15, 0xFF777777);

        // Buttons
        String[] labels = {"Singleplayer", "Multiplayer", "Options", "Mods", "Quit"};
        int[] colors = {0xFF7B61FF, 0xFF00D4FF, 0xFF888888, 0xFF44FF88, 0xFFFF4444};
        int bw = 140, bh = 20;
        int startY = h/2;

        for (int i = 0; i < labels.length; i++) {
            int bx = w/2 - bw/2;
            int by = startY + i * 25;
            boolean hov = mouseX >= bx && mouseX <= bx+bw && mouseY >= by && mouseY <= by+bh;
            ctx.fill(bx, by, bx+bw, by+bh, hov ? ThemeModule.withAlpha(colors[i], 180) : 0xAA111111);
            ctx.fill(bx, by, bx+bw, by+1, colors[i]);
            int tw = textRenderer.getWidth(labels[i]);
            ctx.drawTextWithShadow(textRenderer, labels[i], bx + (bw-tw)/2, by + 6, 0xFFFFFFFF);
        }

        ctx.drawTextWithShadow(textRenderer, "v1.0.0", 3, h-10, 0xFF333333);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        int w = this.width, h = this.height;
        int bw = 140, bh = 20;
        int startY = h/2;
        MinecraftClient mc = MinecraftClient.getInstance();

        for (int i = 0; i < 5; i++) {
            int bx = w/2 - bw/2;
            int by = startY + i * 25;
            if (mx >= bx && mx <= bx+bw && my >= by && my <= by+bh) {
                switch (i) {
                    case 0 -> mc.setScreen(new SelectWorldScreen(this));
                    case 1 -> mc.setScreen(new MultiplayerScreen(this));
                    case 2 -> mc.setScreen(new net.minecraft.client.gui.screen.option.OptionsScreen(this, mc.options));
                    case 3 -> mc.setScreen(new ClickGUI());
                    case 4 -> mc.scheduleStop();
                }
                return true;
            }
        }
        return super.mouseClicked(mx, my, button);
    }

    @Override
    public boolean shouldCloseOnEsc() { return false; }
}
