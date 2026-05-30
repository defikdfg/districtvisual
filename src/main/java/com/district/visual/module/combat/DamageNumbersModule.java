package com.district.visual.module.combat;

import com.district.visual.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DamageNumbersModule extends Module {

    public static int normalColor = 0xFFFF4444;
    public static int critColor = 0xFFFFAA00;
    public static int healColor = 0xFF44FF44;
    public static float scale = 1.0f;
    public static int lifetime = 40; // ticks

    public static class DamageNumber {
        public double x, y, z;
        public float damage;
        public boolean isCrit;
        public int age;
        public float vy = 0.04f;

        public DamageNumber(double x, double y, double z, float damage, boolean isCrit) {
            this.x = x; this.y = y; this.z = z;
            this.damage = damage; this.isCrit = isCrit;
        }
    }

    private static final List<DamageNumber> numbers = new ArrayList<>();

    public DamageNumbersModule() {
        super("DamageNumbers", "Floating damage numbers on hit", Category.COMBAT);
        enabled = true;
    }

    public static void addNumber(Entity target, float damage, boolean isCrit) {
        Vec3d pos = target.getPos().add(
            (Math.random() - 0.5) * 0.5,
            target.getHeight() + 0.3,
            (Math.random() - 0.5) * 0.5
        );
        numbers.add(new DamageNumber(pos.x, pos.y, pos.z, damage, isCrit));
    }

    @Override
    public void onTick(MinecraftClient client) {
        Iterator<DamageNumber> it = numbers.iterator();
        while (it.hasNext()) {
            DamageNumber n = it.next();
            n.y += n.vy;
            n.vy *= 0.98f;
            n.age++;
            if (n.age > lifetime) it.remove();
        }
    }

    public static List<DamageNumber> getNumbers() { return numbers; }
}
