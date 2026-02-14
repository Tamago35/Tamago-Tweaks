package dev.tamago0314.tamagotweaks.mixin;

import dev.tamago0314.tamagotweaks.ToggleKey;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MossAimMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void mossAimTick(CallbackInfo ci) {
        if (!ToggleKey.ENABLED) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;
        if (client.interactionManager == null) return;

        ClientPlayerEntity player = client.player;
        ClientPlayerInteractionManager im = client.interactionManager;
        World world = client.world;

        boolean mainHas = player.getMainHandStack().isOf(Items.BONE_MEAL);
        boolean offHas  = player.getOffHandStack().isOf(Items.BONE_MEAL);
        if (!mainHas && !offHas) return;

        Hand useHand = mainHas ? Hand.MAIN_HAND : Hand.OFF_HAND;

        autoRefillBoneMeal(client, player, useHand);

        if (!(client.crosshairTarget instanceof BlockHitResult hit)) return;

        BlockPos pos = hit.getBlockPos();
        Block block = world.getBlockState(pos).getBlock();
        Direction dir = hit.getSide();

        if (block == Blocks.MOSS_BLOCK) {
            Vec3d hitVec = hit.getPos();
            BlockHitResult bhr = new BlockHitResult(hitVec, dir, pos, false);

            im.interactBlock(player, useHand, bhr);
            player.swingHand(useHand);
            return;
        }

        if (block == Blocks.MOSS_CARPET
                || block == Blocks.AZALEA
                || block == Blocks.FLOWERING_AZALEA
                || block == Blocks.GRASS
                || block == Blocks.TALL_GRASS) {

            im.attackBlock(pos, dir);
            player.swingHand(useHand);
        }
    }

    private void autoRefillBoneMeal(MinecraftClient client, ClientPlayerEntity player, Hand hand) {

        ItemStack stackInHand = hand == Hand.MAIN_HAND
                ? player.getMainHandStack()
                : player.getOffHandStack();

        if (!stackInHand.isOf(Items.BONE_MEAL)) return;
        if (stackInHand.getCount() > 8) return;

        int syncId = player.currentScreenHandler.syncId;

        int targetScreenSlot = (hand == Hand.MAIN_HAND)
                ? player.getInventory().selectedSlot + 36
                : 45;

        for (int invSlot = 9; invSlot < 36; invSlot++) {
            ItemStack invStack = player.getInventory().getStack(invSlot);

            if (invStack.isOf(Items.BONE_MEAL)) {

                client.interactionManager.clickSlot(
                        syncId,
                        invSlot,
                        0,
                        SlotActionType.PICKUP,
                        player
                );

                client.interactionManager.clickSlot(
                        syncId,
                        targetScreenSlot,
                        0,
                        SlotActionType.PICKUP,
                        player
                );

                break;
            }
        }
    }
}