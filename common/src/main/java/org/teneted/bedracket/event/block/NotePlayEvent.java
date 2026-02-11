package org.teneted.bedracket.event.block;

import com.google.common.base.Preconditions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.jetbrains.annotations.NotNull;
import org.teneted.bedracket.event.Cancellable;

/**
 * Called when a note block is being played through player interaction or a
 * redstone current.
 */
public class NotePlayEvent extends BlockEvent implements Cancellable {

    private NoteBlockInstrument instrument;
    private int note;

    private boolean cancelled;

    public NotePlayEvent(@NotNull Block block, @NotNull NoteBlockInstrument instrument, @NotNull int note) {
        super(block);
        this.instrument = instrument;
        this.note = note;
    }

    /**
     * Gets the {@link NoteBlockInstrument} to be used.
     *
     * @return the Instrument
     */
    @NotNull
    public NoteBlockInstrument getInstrument() {
        return this.instrument;
    }

    /**
     * Gets the Node to be played.
     *
     * @return the Note
     */
    @NotNull
    public int getNote() {
        return this.note;
    }

    /**
     * Overrides the {@link NoteBlockInstrument} to be used.
     * <p>
     * Only works when the note block isn't under a player head.
     * For this specific case the 'note_block_sound' property of the
     * player head state takes the priority.
     *
     * @param instrument the Instrument.
     */
    public void setInstrument(@NotNull NoteBlockInstrument instrument) {
        Preconditions.checkArgument(instrument != null, "instrument cannot be null");
        this.instrument = instrument;
    }

    /**
     * Overrides the Note to be played.
     *
     * @param note the Note.
     */
    public void setNote(@NotNull int note) {
        this.note = note;
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
