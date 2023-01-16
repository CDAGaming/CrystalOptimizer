package io.github.cdagaming.crystaloptimizer.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class ClientConnectionMixin {
    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;)V", at = @At("HEAD"))
    private void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        final Minecraft mc = Minecraft.getInstance();
        if (packet instanceof ServerboundInteractPacket) {
            ServerboundInteractPacket interactPacket = (ServerboundInteractPacket) packet;
            interactPacket.dispatch(new ServerboundInteractPacket.Handler() {
                @Override
                public void onInteraction(InteractionHand hand) {
                    // N/A
                }

                @Override
                public void onInteraction(InteractionHand hand, Vec3 pos) {
                    // N/A
                }

                @Override
                public void onAttack() {
                    EntityHitResult entityHitResult;
                    Entity entity;
                    HitResult hitResult = mc.hitResult;
                    if (hitResult == null) {
                        return;
                    }
                    if (hitResult.getType() == HitResult.Type.ENTITY && (entity = (entityHitResult = (EntityHitResult) hitResult).getEntity()) instanceof EndCrystal) {
                        MobEffectInstance weakness = mc.player.getEffect(MobEffects.WEAKNESS);
                        MobEffectInstance strength = mc.player.getEffect(MobEffects.DAMAGE_BOOST);
                        if (!(weakness == null || strength != null && strength.getAmplifier() > weakness.getAmplifier() || ClientConnectionMixin.this.isTool(mc.player.getMainHandItem()))) {
                            return;
                        }
                        entity.kill();
                        entity.setRemoved(Entity.RemovalReason.KILLED);
                        entity.onClientRemoval();
                    }
                }
            });
        }
    }

    private boolean isTool(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof TieredItem) || itemStack.getItem() instanceof HoeItem) {
            return false;
        }
        Tier material = ((TieredItem) itemStack.getItem()).getTier();
        return material == Tiers.DIAMOND || material == Tiers.NETHERITE;
    }
}
