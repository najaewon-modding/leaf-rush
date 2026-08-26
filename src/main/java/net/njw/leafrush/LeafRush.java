package net.njw.leafrush;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(LeafRush.MODID)
public class LeafRush {

    public static final String MODID = "njw_leaf_rush";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LeafRush(IEventBus modEventBus) {
    }
}