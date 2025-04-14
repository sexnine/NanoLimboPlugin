/*
 * Copyright (C) 2020 Nan1t
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ua.nanit.limbo.protocol.packets.login;

import ua.nanit.limbo.connection.ClientConnection;
import ua.nanit.limbo.connection.PlayerPublicKey;
import ua.nanit.limbo.protocol.ByteMessage;
import ua.nanit.limbo.protocol.PacketIn;
import ua.nanit.limbo.protocol.registry.Version;
import ua.nanit.limbo.server.LimboServer;

import java.util.UUID;

public class PacketLoginStart implements PacketIn {

    private String username;
    private PlayerPublicKey playerPublicKey;
    private UUID uuid;

    public String getUsername() {
        return username;
    }

    public PlayerPublicKey getPlayerPublicKey() {
        return playerPublicKey;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void decode(ByteMessage msg, Version version) {
        this.username = msg.readString(256);

        if (version.fromTo(Version.V1_19, Version.V1_19_3)) {
            this.playerPublicKey = msg.readPublicKey();
        }

        if (version.moreOrEqual(Version.V1_19_1)) {
            if (version.moreOrEqual(Version.V1_20_2) || msg.readBoolean()) {
                this.uuid = msg.readUuid();
            }
        }
    }

    @Override
    public void handle(ClientConnection conn, LimboServer server) {
        server.getPacketHandler().handle(conn, this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
