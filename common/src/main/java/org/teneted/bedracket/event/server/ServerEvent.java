package org.teneted.bedracket.event.server;

import net.minecraft.server.MinecraftServer;
import org.teneted.bedracket.event.Event;

/**
 * Miscellaneous server events
 */
public abstract class ServerEvent extends Event {

    private final MinecraftServer server;

    public ServerEvent(MinecraftServer server) {
        this.server = server;
    }

    public ServerEvent(boolean isAsync, MinecraftServer server) {
        super(isAsync);
        this.server = server;
    }

    public MinecraftServer getServer() {
        return server;
    }
}
