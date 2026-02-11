package org.teneted.bedracket.fabric;

import org.teneted.bedracket.BedRacket;
import net.fabricmc.api.ModInitializer;

public final class BedRacketFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BedRacket.init();
        BedRacketEventHandlerFabric.registerAll();
    }
}
