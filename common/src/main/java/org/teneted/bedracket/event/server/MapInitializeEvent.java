package org.teneted.bedracket.event.server;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

public class MapInitializeEvent extends ServerEvent{

    private final MapId mapId;
    private final MapItemSavedData data;

    public MapInitializeEvent(MinecraftServer server, MapId mapId, MapItemSavedData data) {
        super(server);
        this.mapId = mapId;
        this.data = data;
    }

    public MapId getMapId() {
        return mapId;
    }

    public MapItemSavedData getData() {
        return data;
    }
}
