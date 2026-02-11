package org.teneted.bedracket.event.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.teneted.bedracket.event.Cancellable;

/**
 * Called when a LivingEntity loads a crossbow with a projectile.
 */
public class EntityLoadCrossbowEvent extends EntityEvent implements Cancellable {

    private final ItemStack crossbow;
    private final InteractionHand hand;

    private boolean consumeItem = true;
    private boolean cancelled;

    public EntityLoadCrossbowEvent(final LivingEntity entity, final ItemStack crossbow, final InteractionHand hand) {
        super(entity);
        this.crossbow = crossbow;
        this.hand = hand;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * Gets the crossbow {@link ItemStack} being loaded.
     *
     * @return the crossbow involved in this event
     */
    public ItemStack getCrossbow() {
        return this.crossbow;
    }

    /**
     * Gets the hand from which the crossbow was loaded.
     *
     * @return the hand
     */
    public InteractionHand getHand() {
        return this.hand;
    }

    /**
     * @return should the itemstack be consumed
     */
    public boolean shouldConsumeItem() {
        return this.consumeItem;
    }

    /**
     * @param consume should the item be consumed
     */
    public void setConsumeItem(final boolean consume) {
        this.consumeItem = consume;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Set whether to cancel the crossbow being loaded. If canceled, the
     * projectile that would be loaded into the crossbow will not be consumed.
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
}
