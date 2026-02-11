package org.teneted.bedracket.event.entity;


import net.minecraft.world.entity.monster.Creeper;
import org.teneted.bedracket.event.Cancellable;

/**
 * Called when a Creeper is ignited either by a
 * flint and steel, {@link Creeper#ignite()} or
 * {@link Creeper#ignite()} (boolean)}.
 */
public class CreeperIgniteEvent extends EntityEvent implements Cancellable {

    private boolean ignited;
    private boolean cancelled;

    public CreeperIgniteEvent(final Creeper creeper, final boolean ignited) {
        super(creeper);
        this.ignited = ignited;
    }

    @Override
    public Creeper getEntity() {
        return (Creeper) super.getEntity();
    }

    public boolean isIgnited() {
        return this.ignited;
    }

    public void setIgnited(final boolean ignited) {
        this.ignited = ignited;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
}
