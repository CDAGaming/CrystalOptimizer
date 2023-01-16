package io.github.cdagaming.crystaloptimizer.fabric;

import io.github.cdagaming.crystaloptimizer.CrystalOptimizer;
import net.fabricmc.api.ModInitializer;

public class CrystalOptimizerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CrystalOptimizer.init();
    }
}