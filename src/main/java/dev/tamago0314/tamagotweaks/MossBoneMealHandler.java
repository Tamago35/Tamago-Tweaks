package dev.tamago0314.tamagotweaks;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class MossBoneMealHandler {

    public static void register() {

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

            ItemStack stack = player.getStackInHand(hand);

            if (!(stack.getItem() instanceof BoneMealItem)) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);

            boolean isObstacle =
                    state.isOf(Blocks.MOSS_CARPET) ||
                            state.isOf(Blocks.AZALEA) ||
                            state.isOf(Blocks.FLOWERING_AZALEA);

            if (!isObstacle) {
                return ActionResult.PASS;
            }

            BlockPos below = pos.down();
            BlockState belowState = world.getBlockState(below);

            if (belowState.isOf(Blocks.MOSS_BLOCK)) {

                world.breakBlock(pos, true, player);

                BlockHitResult newHit = new BlockHitResult(
                        hitResult.getPos(),
                        hitResult.getSide(),
                        below,
                        false
                );

                ItemUsageContext context = new ItemUsageContext(player, hand, newHit);
                return stack.useOnBlock(context);
            }

            return ActionResult.PASS;
        });
    }
}