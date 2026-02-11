package org.teneted.bedracket.event.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.teneted.bedracket.event.Cancellable;

/**
 * Event called when a Crafter is about to craft an item.
 */
public class CrafterCraftEvent extends BlockEvent implements Cancellable {

    private final CraftingRecipe recipe;
    private ItemStack result;

    private boolean cancelled;

    public CrafterCraftEvent(@NotNull Block crafter, @NotNull CraftingRecipe recipe, @NotNull ItemStack result) {
        super(crafter);
        this.result = result;
        this.recipe = recipe;
    }

    /**
     * Gets the result for the craft.
     *
     * @return the result for the craft
     */
    @NotNull
    public ItemStack getResult() {
        return this.result.copy();
    }

    /**
     * Sets the result of the craft.
     *
     * @param result the result of the craft
     */
    public void setResult(@NotNull ItemStack result) {
        this.result = result.copy();
    }

    /**
     * Gets the recipe that was used to craft this item.
     *
     * @return the recipe that was used to craft this item
     */
    @NotNull
    public CraftingRecipe getRecipe() {
        return this.recipe;
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
