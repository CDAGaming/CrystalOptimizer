package io.github.cdagaming.crystaloptimizer.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
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
            interactPacket.handle(new ServerGamePacketListener() {

                @Override
                public void onDisconnect(Component component) {

                }

                @Override
                public Connection getConnection() {
                    return null;
                }

                @Override
                public void handleAnimate(ServerboundSwingPacket serverboundSwingPacket) {

                }

                @Override
                public void handleChat(ServerboundChatPacket serverboundChatPacket) {

                }

                @Override
                public void handleClientCommand(ServerboundClientCommandPacket serverboundClientCommandPacket) {

                }

                @Override
                public void handleClientInformation(ServerboundClientInformationPacket serverboundClientInformationPacket) {

                }

                @Override
                public void handleContainerAck(ServerboundContainerAckPacket serverboundContainerAckPacket) {

                }

                @Override
                public void handleContainerButtonClick(ServerboundContainerButtonClickPacket serverboundContainerButtonClickPacket) {

                }

                @Override
                public void handleContainerClick(ServerboundContainerClickPacket serverboundContainerClickPacket) {

                }

                @Override
                public void handlePlaceRecipe(ServerboundPlaceRecipePacket serverboundPlaceRecipePacket) {

                }

                @Override
                public void handleContainerClose(ServerboundContainerClosePacket serverboundContainerClosePacket) {

                }

                @Override
                public void handleCustomPayload(ServerboundCustomPayloadPacket serverboundCustomPayloadPacket) {

                }

                @Override
                public void handleInteract(ServerboundInteractPacket serverboundInteractPacket) {
                    EntityHitResult entityHitResult;
                    Entity entity;
                    HitResult hitResult = mc.hitResult;
                    if (hitResult == null || serverboundInteractPacket.getAction() != ServerboundInteractPacket.Action.ATTACK) {
                        return;
                    }
                    if (hitResult.getType() == HitResult.Type.ENTITY && (entity = (entityHitResult = (EntityHitResult) hitResult).getEntity()) instanceof EndCrystal) {
                        MobEffectInstance weakness = mc.player.getEffect(MobEffects.WEAKNESS);
                        MobEffectInstance strength = mc.player.getEffect(MobEffects.DAMAGE_BOOST);
                        if (!(weakness == null || strength != null && strength.getAmplifier() > weakness.getAmplifier() || ClientConnectionMixin.this.isTool(mc.player.getMainHandItem()))) {
                            return;
                        }
                        entity.kill();
                        //entity.setRemoved(Entity.RemovalReason.KILLED);
                        //entity.onClientRemoval();
                    }
                }

                @Override
                public void handleKeepAlive(ServerboundKeepAlivePacket serverboundKeepAlivePacket) {

                }

                @Override
                public void handleMovePlayer(ServerboundMovePlayerPacket serverboundMovePlayerPacket) {

                }

                @Override
                public void handlePlayerAbilities(ServerboundPlayerAbilitiesPacket serverboundPlayerAbilitiesPacket) {

                }

                @Override
                public void handlePlayerAction(ServerboundPlayerActionPacket serverboundPlayerActionPacket) {

                }

                @Override
                public void handlePlayerCommand(ServerboundPlayerCommandPacket serverboundPlayerCommandPacket) {

                }

                @Override
                public void handlePlayerInput(ServerboundPlayerInputPacket serverboundPlayerInputPacket) {

                }

                @Override
                public void handleSetCarriedItem(ServerboundSetCarriedItemPacket serverboundSetCarriedItemPacket) {

                }

                @Override
                public void handleSetCreativeModeSlot(ServerboundSetCreativeModeSlotPacket serverboundSetCreativeModeSlotPacket) {

                }

                @Override
                public void handleSignUpdate(ServerboundSignUpdatePacket serverboundSignUpdatePacket) {

                }

                @Override
                public void handleUseItemOn(ServerboundUseItemOnPacket serverboundUseItemOnPacket) {

                }

                @Override
                public void handleUseItem(ServerboundUseItemPacket serverboundUseItemPacket) {

                }

                @Override
                public void handleTeleportToEntityPacket(ServerboundTeleportToEntityPacket serverboundTeleportToEntityPacket) {

                }

                @Override
                public void handleResourcePackResponse(ServerboundResourcePackPacket serverboundResourcePackPacket) {

                }

                @Override
                public void handlePaddleBoat(ServerboundPaddleBoatPacket serverboundPaddleBoatPacket) {

                }

                @Override
                public void handleMoveVehicle(ServerboundMoveVehiclePacket serverboundMoveVehiclePacket) {

                }

                @Override
                public void handleAcceptTeleportPacket(ServerboundAcceptTeleportationPacket serverboundAcceptTeleportationPacket) {

                }

                @Override
                public void handleRecipeBookSeenRecipePacket(ServerboundRecipeBookSeenRecipePacket serverboundRecipeBookSeenRecipePacket) {

                }

                @Override
                public void handleRecipeBookChangeSettingsPacket(ServerboundRecipeBookChangeSettingsPacket serverboundRecipeBookChangeSettingsPacket) {

                }

                @Override
                public void handleSeenAdvancements(ServerboundSeenAdvancementsPacket serverboundSeenAdvancementsPacket) {

                }

                @Override
                public void handleCustomCommandSuggestions(ServerboundCommandSuggestionPacket serverboundCommandSuggestionPacket) {

                }

                @Override
                public void handleSetCommandBlock(ServerboundSetCommandBlockPacket serverboundSetCommandBlockPacket) {

                }

                @Override
                public void handleSetCommandMinecart(ServerboundSetCommandMinecartPacket serverboundSetCommandMinecartPacket) {

                }

                @Override
                public void handlePickItem(ServerboundPickItemPacket serverboundPickItemPacket) {

                }

                @Override
                public void handleRenameItem(ServerboundRenameItemPacket serverboundRenameItemPacket) {

                }

                @Override
                public void handleSetBeaconPacket(ServerboundSetBeaconPacket serverboundSetBeaconPacket) {

                }

                @Override
                public void handleSetStructureBlock(ServerboundSetStructureBlockPacket serverboundSetStructureBlockPacket) {

                }

                @Override
                public void handleSelectTrade(ServerboundSelectTradePacket serverboundSelectTradePacket) {

                }

                @Override
                public void handleEditBook(ServerboundEditBookPacket serverboundEditBookPacket) {

                }

                @Override
                public void handleEntityTagQuery(ServerboundEntityTagQuery serverboundEntityTagQuery) {

                }

                @Override
                public void handleBlockEntityTagQuery(ServerboundBlockEntityTagQuery serverboundBlockEntityTagQuery) {

                }

                @Override
                public void handleSetJigsawBlock(ServerboundSetJigsawBlockPacket serverboundSetJigsawBlockPacket) {

                }

                @Override
                public void handleJigsawGenerate(ServerboundJigsawGeneratePacket serverboundJigsawGeneratePacket) {

                }

                @Override
                public void handleChangeDifficulty(ServerboundChangeDifficultyPacket serverboundChangeDifficultyPacket) {

                }

                @Override
                public void handleLockDifficulty(ServerboundLockDifficultyPacket serverboundLockDifficultyPacket) {

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
