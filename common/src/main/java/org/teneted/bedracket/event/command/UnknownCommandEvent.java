package org.teneted.bedracket.event.command;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.teneted.bedracket.event.Event;

public class UnknownCommandEvent extends Event {

    private final CommandSourceStack commandSource;
    private final String commandLine;
    private @Nullable Component message;

    public UnknownCommandEvent(final CommandSourceStack commandSource, final String commandLine, final @Nullable Component message) {
        super(false);
        this.commandSource = commandSource;
        this.commandLine = commandLine;
        this.message = message;
    }

    /**
     * Gets the command source associated with this event
     *
     * @return the {@link CommandSourceStack}
     */
    public CommandSourceStack getCommandSource() {
        return this.commandSource;
    }

    /**
     * Gets the command that was sent
     *
     * @return command sent
     */
    public String getCommandLine() {
        return this.commandLine;
    }

    /**
     * Gets the message that will be returned
     *
     * @return unknown command message
     */
    public @Nullable Component getMessage() {
        return this.message;
    }

    /**
     * Sets the message that will be returned
     * <p>
     * Set to {@code null} to avoid any message being sent
     *
     * @param message the message to be returned, or {@code null}
     */
    public void setMessage(@Nullable Component message) {
        this.message = message;
    }
}
