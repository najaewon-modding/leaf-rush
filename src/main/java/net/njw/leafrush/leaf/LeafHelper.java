package net.njw.leafrush.leaf;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class LeafHelper {
    private LeafHelper() {}

    public static boolean isDecayCandidate(ServerLevel level, BlockPos pos, BlockState state) {
        if (!level.isLoaded(pos)) return false;
        if (!state.is(BlockTags.LEAVES)) return false;
        return !state.hasProperty(LeavesBlock.PERSISTENT) || !state.getValue(LeavesBlock.PERSISTENT);
    }

    public static void tryRandomTickLeaf(ServerLevel level, BlockPos pos) {
        if (!level.isLoaded(pos)) return;
        BlockState state = level.getBlockState(pos);
        if (!isDecayCandidate(level, pos, state)) return;
        state.randomTick(level, pos, level.getRandom());
    }
}