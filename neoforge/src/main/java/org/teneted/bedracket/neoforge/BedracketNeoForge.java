package org.teneted.bedracket.neoforge;

import org.teneted.bedracket.Bedracket;
import net.neoforged.fml.common.Mod;

@Mod(Bedracket.MOD_ID)
public final class BedracketNeoForge {
    public BedracketNeoForge() {
        // Run our common setup.
        Bedracket.init();
    }
}
