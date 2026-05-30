package com.district.visual.gui;

import com.district.visual.DistrictVisualClient;
import com.district.visual.module.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.List;

public class ClickGUI extends Screen {

    private Module.Category[] categories = Module.Category.values();
    private int selectedCat = 0;
    private int scroll = 0;

    public ClickGUI() {
        super(Text.literal("DistrictVisual GUI"));
    }

    @Override
    protected void init() {
        for (int i = 0; i < categories.length; i++) {
            final int idx = i;
            addDrawableChild(ButtonWidget.builder(
                Text.literal(categories[i].name()),
                btn -> { selectedCat = idx; scroll = 0; clearAndInit(); }
            ).dimensions(10 + i * 80, 10, 75, 20).build());
        }

        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        for (int i = scroll; i < Math.min(mods.size(), scroll + 15); i++) {
            final Module m = mods.get(i);
            final int row = i - scroll;
            addDrawableChild(ButtonWidget.builder(
                Text.literal(m.getName() + " [" + (m.isEnabled() ? "ON" : "OFF") + "]"),
                btn -> { m.toggle(); clearAndInit(); }
            ).dimensions(10, 35 + row * 22, 200, 20).build());
        }
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, this.width, this.height, 0xFF000000);
        ctx.drawTextWithShadow(textRenderer, "districtvisual v1.0.0  |  RShift = close", 10, this.height - 12, 0xFF7B61FF);
        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double h, double v) {
        List<Module> mods = DistrictVisualClient.getInstance().getModuleManager().getByCategory(categories[selectedCat]);
        scroll = (int) Math.max(0, Math.min(mods.size() - 1, scroll - v));
        clearAndInit();
        return true;
    }

    @Override
    public boolean shouldPause() { return false; }
}
