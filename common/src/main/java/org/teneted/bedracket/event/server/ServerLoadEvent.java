package org.teneted.bedracket.event.server;

import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called when either the server startup or reload has completed.
 */
public class ServerLoadEvent extends ServerEvent {


    private final LoadType type;

    public ServerLoadEvent(MinecraftServer server, @NotNull LoadType type) {
        super(server);
        this.type = type;
    }

    /**
     * Gets the context in which the server was loaded.
     *
     * @return the context in which the server was loaded
     */
    @NotNull
    public LoadType getType() {
        return this.type;
    }

    /**
     * Represents the context in which the enclosing event has been completed.
     */
    public enum LoadType {
        STARTUP,
        RELOAD
    }
}
