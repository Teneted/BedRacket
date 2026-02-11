package org.teneted.bedracket.event.block;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BellResonateEvent extends BlockEvent {

    private final List<LivingEntity> resonatedEntities;

    public BellResonateEvent(@NotNull Block bell, @NotNull List<LivingEntity> resonatedEntities) {
        super(bell);
        this.resonatedEntities = resonatedEntities;
    }

    /**
     * Get a mutable list of all {@link LivingEntity entities} to be
     * highlighted by the bell's resonating. This list can be added to or
     * removed from to change which entities are highlighted, and may be empty
     * if no entities were resonated as a result of this event.
     * <p>
     * While the highlighted entities will change, the particles that display
     * over a resonated entity and their colors will not. This is handled by the
     * client and cannot be controlled by the server.
     *
     * @return a list of resonated entities
     */
    @NotNull
    public List<LivingEntity> getResonatedEntities() {
        return this.resonatedEntities;
    }
}
