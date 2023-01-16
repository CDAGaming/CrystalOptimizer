package io.github.cdagaming.crystaloptimizer.mixin;

import io.github.cdagaming.crystaloptimizer.CrystalOptimizer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onPreTick(CallbackInfo info) {
        Iterator<Map.Entry<Entity, Integer>> iterator = CrystalOptimizer.toKill.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Entity, Integer> entry = iterator.next();
            Entity entity = entry.getKey();
            int delay = entry.getValue() - 1;
            if (delay == 0) {
                iterator.remove();
                if (entity.isAlive()) continue;
                entity.kill();
                entity.setRemoved(Entity.RemovalReason.KILLED);
                entity.onClientRemoval();
                continue;
            }
            entry.setValue(delay);
        }
    }
}
