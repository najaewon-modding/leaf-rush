package net.njw.leafrush.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.njw.leafrush.leaf.LeafScheduler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelAirTransitionMixin {
    @Inject(method = "setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", at = @At("TAIL"))
    private void leafRush$afterSetBlock(BlockPos pos, BlockState newState, int flags, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() || !newState.isAir()) return;
        if (!((Object) this instanceof ServerLevel level)) return;
        for (Direction direction : Direction.values()) LeafScheduler.enqueueIfCandidate(level, pos.relative(direction), LeafScheduler.getRandomDelay(level));
    }
}