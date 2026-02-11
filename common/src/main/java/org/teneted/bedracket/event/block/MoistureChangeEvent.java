package org.teneted.bedracket.event.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.teneted.bedracket.event.Cancellable;

/**
 * Called when the moisture level of a soil block changes.
 */
public class MoistureChangeEvent extends BlockEvent implements Cancellable {

    private final BlockState newState;
    private boolean cancelled;

    public MoistureChangeEvent(@NotNull final Block block, @NotNull final BlockState newState) {
        super(block);
        this.newState = newState;
    }

    /**
     * Gets the new state of the affected block.
     *
     * @return new block state
     */
    @NotNull
    public BlockState getNewState() {
        return this.newState;
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
