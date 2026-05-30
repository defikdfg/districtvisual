package com.district.visual.gui;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGUI extends Screen {

    private static final int PANEL_W = 200;
    private static final int PANEL_H = 26;
    private static final int CAT_H = 26;
    private Module.Category[] categories = Module.Category.values();
    private int selectedCat = 0;
    private int scroll = 0;

    public ClickGUI() {
        super(Text.literal("DistrictVisual GUI"));
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        // Dark overlay
        ctx.fill(0, 0, this.width, this.height, 0xCC000000);

        if (textRenderer == null) { super.render(ctx, mouseX, mouseY, delta); return; }

        int sx = 20;
        int sy = 20;

        // Title bar
        ctx.fill(sx, sy, sx + 300, sy + 18, 0xFF0D0D1A);
        ctx.fill(sx, sy, sx + 300, sy + 1, 0xFF7B61FF);
        ctx.drawTextWithShadow(textRenderer, "districtvisual  v1.0.0", sx + 5, sy + 5, 0xFF7B61FF);
        sy += 22;

        // Category tabs
        for (int i = 0; i < categories.length; i++) {
            int tx = sx + i * 78;
            boolean sel = i == selectedCat;
            ctx.fill(tx, sy, tx + 75, sy + CAT_H, sel ? 0xFF7B61FF : 0xFF111118);
            ctx.fill(tx, sy, tx + 75, sy + 1, sel ? 0xFFFFFFFF : 0xFF7B61FF);
            ctx.drawTextWithShadow(textRenderer, categories[i].name(), tx + 6, sy + 9, sel ? 0xFFFFFFFF : 0xFFAAAAAA);
        }
        sy += CAT_H + 3;

        // Module list
        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        for (int i = scroll; i < Math.min(mods.size(), scroll + 16); i++) {
            Module m = mods.get(i);
            boolean hov = mouseX >= sx && mouseX <= sx + PANEL_W && mouseY >= sy && mouseY <= sy + PANEL_H;
            int bg = m.isEnabled() ? 0xFF1A1530 : (hov ? 0xFF1A1A22 : 0xFF0F0F18);
            ctx.fill(sx, sy, sx + PANEL_W, sy + PANEL_H, bg);
            ctx.fill(sx, sy, sx + 3, sy + PANEL_H, m.isEnabled() ? 0xFF7B61FF : 0xFF333344);
            ctx.fill(sx, sy + PANEL_H - 1, sx + PANEL_W, sy + PANEL_H, 0xFF1A1A2A);
            ctx.drawTextWithShadow(textRenderer, m.getName(), sx + 10, sy + 9, 0xFFFFFFFF);
            String status = m.isEnabled() ? "ON" : "OFF";
            int statusColor = m.isEnabled() ? 0xFF44FF88 : 0xFFFF4455;
            ctx.drawTextWithShadow(textRenderer, status, sx + PANEL_W - 25, sy + 9, statusColor);
            sy += PANEL_H;
        }

        // Bottom hint
        ctx.fill(20, this.height - 16, this.width - 20, this.height - 4, 0xFF0D0D1A);
        ctx.drawTextWithShadow(textRenderer, "Click = toggle    Scroll = scroll    RShift = close", 25, this.height - 13, 0xFF555566);

        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mx, double my, int btn) {
        int sx = 20;
        int sy = 20 + 22 + CAT_H + 3;

        for (int i = 0; i < categories.length; i++) {
            int tx = 20 + i * 78;
            if (mx >= tx && mx <= tx + 75 && my >= 42 && my <= 42 + CAT_H) {
                selectedCat = i; scroll = 0; return true;
            }
        }

        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        for (int i = scroll; i < Math.min(mods.size(), scroll + 16); i++) {
            if (mx >= sx && mx <= sx + PANEL_W && my >= sy && my <= sy + PANEL_H) {
                mods.get(i).toggle(); return true;
            }
            sy += PANEL_H;
        }
        return super.mouseClicked(mx, my, btn);
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double h, double v) {
       List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        scroll = (int) Math.max(0, Math.min(mods.size() - 1, scroll - v));
        return true;
    }

    @Override
    public boolean shouldPause() { return false; }
}
