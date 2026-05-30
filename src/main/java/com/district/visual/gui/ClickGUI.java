package com.district.visual.gui;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.Module;
import com.district.visual.module.visual.ThemeModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGUI extends Screen {

    private static final int PANEL_W = 160;
    private static final int PANEL_H = 22;
    private static final int CATEGORY_H = 28;
    private Module.Category[] categories = Module.Category.values();
    private int selectedCat = 0;
    private int scroll = 0;

    public ClickGUI() {
        super(Text.literal("DistrictVisual GUI"));
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        // Dim background
        ctx.fill(0, 0, this.width, this.height, 0xBB000000);

        int startX = 20;
        int startY = 20;

        // === Title ===
        if (textRenderer != null) {
            ctx.drawTextWithShadow(textRenderer, "§bdistrictvisual §7v1.0.0", startX, startY, 0xFFFFFFFF);
        }
        startY += 18;

        // === Category tabs ===
        for (int i = 0; i < categories.length; i++) {
            int tx = startX + i * 90;
            boolean sel = i == selectedCat;
            int tabBg = sel ? ThemeModule.withAlpha(ThemeModule.primaryColor, 180) : 0xAA111111;
            ctx.fill(tx, startY, tx + 85, startY + CATEGORY_H, tabBg);
            if (sel) ctx.fill(tx, startY, tx + 85, startY + 2, ThemeModule.primaryColor);
            if (textRenderer != null) {
                String catName = categories[i].name();
                ctx.drawTextWithShadow(textRenderer, catName, tx + 5, startY + (CATEGORY_H - 8) / 2, 0xFFFFFFFF);
            }
        }
        startY += CATEGORY_H + 5;

        // === Module list ===
        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        int vy = startY;
        for (int i = scroll; i < Math.min(mods.size(), scroll + 15); i++) {
            Module m = mods.get(i);
            boolean hov = mouseX >= startX && mouseX <= startX + PANEL_W && mouseY >= vy && mouseY <= vy + PANEL_H;
            int bg = m.isEnabled() ? ThemeModule.withAlpha(ThemeModule.primaryColor, 140) : (hov ? 0xAA1A1A1A : 0xAA111111);
            ctx.fill(startX, vy, startX + PANEL_W, vy + PANEL_H, bg);
            ctx.fill(startX, vy, startX + 2, vy + PANEL_H, m.isEnabled() ? ThemeModule.primaryColor : 0xFF333333);
            if (textRenderer != null) {
                ctx.drawTextWithShadow(textRenderer, m.getName(), startX + 8, vy + (PANEL_H - 8) / 2, 0xFFFFFFFF);
                String status = m.isEnabled() ? "§aON" : "§cOFF";
                ctx.drawTextWithShadow(textRenderer, status, startX + PANEL_W - 26, vy + (PANEL_H - 8) / 2, 0xFFFFFFFF);
            }
            vy += PANEL_H + 2;
        }

        // Hint bottom
        if (textRenderer != null) {
            ctx.drawTextWithShadow(textRenderer, "§7Click to toggle  •  RShift to close", startX, this.height - 15, 0xFF555555);
        }

        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        int startX = 20;
        int startY = 38 + CATEGORY_H + 5;

        // Category click
        for (int i = 0; i < categories.length; i++) {
            int tx = startX + i * 90;
            if (mx >= tx && mx <= tx + 85 && my >= 38 && my <= 38 + CATEGORY_H) {
                selectedCat = i;
                scroll = 0;
                return true;
            }
        }

        // Module click
        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        int vy = startY;
        for (int i = scroll; i < Math.min(mods.size(), scroll + 15); i++) {
            if (mx >= startX && mx <= startX + PANEL_W && my >= vy && my <= vy + PANEL_H) {
                mods.get(i).toggle();
                return true;
            }
            vy += PANEL_H + 2;
        }
        return super.mouseClicked(mx, my, btn);
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double hScroll, double vScroll) {
        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        scroll = (int) Math.max(0, Math.min(mods.size() - 1, scroll - vScroll));
        return true;
    }

    @Override
    public boolean shouldPause() { return false; }
}
