package org.teneted.bedracket.event;

import org.jetbrains.annotations.NotNull;

/**
 * Interface which defines the class for event call backs to mods
 */
public interface EventExecutor {
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException;
}
