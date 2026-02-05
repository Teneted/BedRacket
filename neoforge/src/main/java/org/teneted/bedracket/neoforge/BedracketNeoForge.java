package org.teneted.bedracket.neoforge;

import org.teneted.bedracket.BedRacket;
import net.neoforged.fml.common.Mod;

@Mod(BedRacket.MOD_ID)
public final class BedRacketNeoForge {
    public BedRacketNeoForge() {
        // Run our common setup.
        BedRacket.init();
    }
}
