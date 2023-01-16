package io.github.cdagaming.crystaloptimizer.forge;

import io.github.cdagaming.crystaloptimizer.CrystalOptimizer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CrystalOptimizer.MOD_ID)
public class CrystalOptimizerForge {
    public CrystalOptimizerForge() {
        CrystalOptimizer.init();
    }
}