package org.teneted.bedracket.event.block;

import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.teneted.bedracket.event.Cancellable;

/**
 * Called when leaves are decaying naturally.
 * <p>
 * If this event is cancelled, the leaves will not decay.
 */
public class LeavesDecayEvent extends BlockEvent implements Cancellable {

    private boolean cancelled;

    public LeavesDecayEvent(@NotNull final Block block) {
        super(block);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
