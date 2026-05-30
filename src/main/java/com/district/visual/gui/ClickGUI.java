package com.district.visual.gui;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGUI extends Screen {

   private static final int PANEL_W = 180;
   private static final int PANEL_H = 24;
   private static final int CAT_H = 24;
   private Module.Category[] categories = Module.Category.values();
   private int selectedCat = 0;
   private int scroll = 0;

   public ClickGUI() {
       super(Text.literal("DistrictVisual GUI"));
   }

   @Override
   public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
       ctx.fill(0, 0, this.width, this.height, 0xDD000000);
       int sx = 15;
       int sy = 15;
       ctx.drawTextWithShadow(textRenderer, "districtvisual v1.0.0", sx, sy, 0xFF7B61FF);
       sy += 20;
       for (int i = 0; i < categories.length; i++) {
           int tx = sx + i * 75;
           boolean sel = i == selectedCat;
           ctx.fill(tx, sy, tx + 72, sy + CAT_H, sel ? 0xFF7B61FF : 0xFF1A1A1A);
           ctx.fill(tx, sy, tx + 72, sy + 1, 0xFF7B61FF);
           ctx.drawTextWithShadow(textRenderer, categories[i].name(), tx + 4, sy + 8, 0xFFFFFFFF);
       }
       sy += CAT_H + 4;
       List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
       for (int i = scroll; i < Math.min(mods.size(), scroll + 18); i++) {
           Module m = mods.get(i);
           boolean hov = mouseX >= sx && mouseX <= sx + PANEL_W && mouseY >= sy && mouseY <= sy + PANEL_H;
           int bg = m.isEnabled() ? 0xFF4A3E8F : (hov ? 0xFF222222 : 0xFF161616);
           ctx.fill(sx, sy, sx + PANEL_W, sy + PANEL_H, bg);
           ctx.fill(sx, sy, sx + 3, sy + PANEL_H, m.isEnabled() ? 0xFF7B61FF : 0xFF444444);
           ctx.drawTextWithShadow(textRenderer, m.getName(), sx + 8, sy + 8, 0xFFFFFFFF);
           int statusColor = m.isEnabled() ? 0xFF44FF44 : 0xFFFF4444;
           ctx.drawTextWithShadow(textRenderer, m.isEnabled() ? "ON" : "OFF", sx + PANEL_W - 28, sy + 8, statusColor);
           sy += PANEL_H + 1;
       }
       ctx.drawTextWithShadow(textRenderer, "Click=toggle  Scroll=scroll  RShift=close", 15, this.height - 12, 0xFF555555);
       super.render(ctx, mouseX, mouseY, delta);
   }

   @Override
   public boolean mouseClicked(double mx, double my, int btn) {
       int sx = 15;
       int sy = 35 + CAT_H + 4;
       for (int i = 0; i < categories.length; i++) {
           int tx = 15 + i * 75;
           if (mx >= tx && mx <= tx + 72 && my >= 35 && my <= 35 + CAT_H) {
               selectedCat = i; scroll = 0; return true;
           }
       }
       List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
       for (int i = scroll; i < Math.min(mods.size(), scroll + 18); i++) {
           if (mx >= sx && mx <= sx + PANEL_W && my >= sy && my <= sy + PANEL_H) {
               mods.get(i).toggle(); return true;
           }
           sy += PANEL_H + 1;
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
