package net.njw.leafrush.leaf;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.njw.leafrush.LeafRush;

@EventBusSubscriber(modid = LeafRush.MODID)
public final class LeafRushEvents {
    private LeafRushEvents() {}

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        LeafScheduler.tick(event.getServer());
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        LeafScheduler.clear();
    }
}