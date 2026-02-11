package org.teneted.bedracket.event.block;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.teneted.bedracket.event.Event;

public abstract class BlockEvent extends Event {

    protected Block block;

    protected BlockEvent(@NotNull final Block block) {
        this.block = block;
    }

    /**
     * Gets the block involved in this event.
     *
     * @return The Block which block is involved in this event
     */
    @NotNull
    public final Block getBlock() {
        return this.block;
    }
}
