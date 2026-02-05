package org.teneted.bedracket.fabric;

import org.teneted.bedracket.Bedracket;
import net.fabricmc.api.ModInitializer;

public final class BedracketFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Bedracket.init();
    }
}
