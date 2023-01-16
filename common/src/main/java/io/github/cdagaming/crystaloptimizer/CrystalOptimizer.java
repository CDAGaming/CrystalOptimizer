package io.github.cdagaming.crystaloptimizer;

import net.minecraft.world.entity.Entity;

import java.util.HashMap;

public class CrystalOptimizer {
    public static final String MOD_ID = "crystaloptimizer";
    public static final HashMap<Entity, Integer> toKill = new HashMap<>();

    public static void init() {
        toKill.clear();
    }
}