package net.njw.leafrush.leaf;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class LeafScheduler {
    private static final int MIN_DELAY = 4;
    private static final int MAX_DELAY = 12;
    private static long tickCounter;
    private static final Long2ObjectOpenHashMap<Map<ResourceKey<Level>, Set<BlockPos>>> TIMELINE = new Long2ObjectOpenHashMap<>();

    private LeafScheduler() {}

    public static int getRandomDelay(ServerLevel level) {
        if (MIN_DELAY >= MAX_DELAY) return MIN_DELAY;
        return MIN_DELAY + level.getRandom().nextInt(MAX_DELAY - MIN_DELAY + 1);
    }

    public static void enqueueIfCandidate(ServerLevel level, BlockPos pos, int delay) {
        if (!level.isLoaded(pos)) return;
        BlockState state = level.getBlockState(pos);
        if (!LeafHelper.isDecayCandidate(level, pos, state)) return;
        long targetTick = tickCounter + Math.max(1, delay);
        Map<ResourceKey<Level>, Set<BlockPos>> byDimension = TIMELINE.computeIfAbsent(targetTick, tick -> new HashMap<>());
        byDimension.computeIfAbsent(level.dimension(), dimension -> new HashSet<>()).add(pos.immutable());
    }

    public static void tick(MinecraftServer server) {
        tickCounter++;
        Map<ResourceKey<Level>, Set<BlockPos>> scheduled = TIMELINE.remove(tickCounter);
        if (scheduled == null) return;
        for (Map.Entry<ResourceKey<Level>, Set<BlockPos>> entry : scheduled.entrySet()) {
            ServerLevel level = server.getLevel(entry.getKey());
            if (level == null) continue;
            for (BlockPos pos : entry.getValue()) LeafHelper.tryRandomTickLeaf(level, pos);
        }
    }

    public static void clear() {
        TIMELINE.clear();
        tickCounter = 0;
    }
}