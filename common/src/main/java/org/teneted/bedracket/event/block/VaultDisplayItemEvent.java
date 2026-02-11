package org.teneted.bedracket.event.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.teneted.bedracket.event.Cancellable;

/**
 * Called when a vault in a trial chamber is about to display an item.
 */
public class VaultDisplayItemEvent extends BlockEvent implements Cancellable {

    private ItemStack displayItem;
    private boolean cancelled;

    public VaultDisplayItemEvent(@NotNull Block vault, @Nullable ItemStack displayItem) {
        super(vault);
        this.displayItem = displayItem;
    }

    /**
     * Gets the item that will be displayed inside the vault.
     *
     * @return the item to be displayed
     */
    @Nullable
    public ItemStack getDisplayItem() {
        return this.displayItem;
    }

    /**
     * Sets the item that will be displayed inside the vault.
     *
     * @param displayItem the item to be displayed
     */
    public void setDisplayItem(@Nullable ItemStack displayItem) {
        this.displayItem = displayItem;
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
