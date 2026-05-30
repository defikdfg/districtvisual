package com.district.visual.gui;

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
        ctx.fill(0, 0, w, h, 0xFF0A0A14);
        ctx.fillGradient(0, 0, w, h/3, 0x557B61FF, 0x00000000);
        if (textRenderer == null) {
            super.render(ctx, mouseX, mouseY, delta);
            return;
        }
        ctx.getMatrices().push();
        ctx.getMatrices().scale(2f, 2f, 1f);
        ctx.drawTextWithShadow(textRenderer, "districtvisual", w/4 - 40, h/6, 0xFF7B61FF);
        ctx.getMatrices().pop();
        String sub = "1.21.4  Visual Mod  Fabric";
        ctx.drawTextWithShadow(textRenderer, sub, w/2 - textRenderer.getWidth(sub)/2, h/4 + 8, 0xFF555555);
        String[] labels = {"Singleplayer", "Multiplayer", "Options", "Mods (RShift)", "Quit"};
        int[] colors = {0xFF7B61FF, 0xFF00D4FF, 0xFF888888, 0xFF44FF88, 0xFFFF4444};
        int bw = 200;
        int bh = 24;
        for (int i = 0; i < labels.length; i++) {
            int bx = w/2 - bw/2;
            int by = h/2 - 20 + i * 28;
            boolean hov = mouseX >= bx && mouseX <= bx+bw && mouseY >= by && mouseY <= by+bh;
            ctx.fill(bx, by, bx+bw, by+bh, hov ? 0xFF1E1E2E : 0xFF111118);
            ctx.fill(bx, by, bx+3, by+bh, colors[i]);
            ctx.fill(bx, by, bx+bw, by+1, hov ? colors[i] : 0xFF333333);
            ctx.drawTextWithShadow(textRenderer, labels[i], bx+12, by+8, 0xFFFFFFFF);
            if (hov) ctx.drawTextWithShadow(textRenderer, ">", bx+bw-12, by+8, colors[i]);
        }
        ctx.drawTextWithShadow(textRenderer, "districtvisual v1.0.0", 4, h-10, 0xFF333333);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int button) {
        int bw = 200;
        int bh = 24;
        MinecraftClient mc = MinecraftClient.getInstance();
        for (int i = 0; i < 5; i++) {
            int bx = width/2 - bw/2;
            int by = height/2 - 20 + i * 28;
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
