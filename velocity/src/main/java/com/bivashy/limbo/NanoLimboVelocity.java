package com.bivashy.limbo;

import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;
import com.bivashy.limbo.command.LampVelocityCommandHandler;
import com.bivashy.limbo.config.LimboConfig;
import com.bivashy.limbo.config.model.VelocityLimboServer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.ServerInfo;

import ua.nanit.limbo.NanoLimbo;
import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.CommandHandler;
import ua.nanit.limbo.server.LimboServer;

@Plugin(id = "nanolimbovelocity", name = "NanoLimboVelocity", version = "1.0.14", authors = "bivashy, Nan1t")
public class NanoLimboVelocity {
    static {
        NanoLimbo.class.getName(); // For preventing shadow jar minimizing
    }

    private static NanoLimboVelocity instance;
    private final Map<String, LimboServer> servers = new HashMap<>();
    private final ProxyServer server;
    private final Path dataFolder;
    private final LimboConfig limboConfig;

    @Inject
    public NanoLimboVelocity(ProxyServer server, @DataDirectory Path dataFolder) {
        instance = this;
        this.server = server;
        this.dataFolder = dataFolder;
        this.limboConfig = new LimboConfig(this);
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent e) {
        CommandHandler<Command> commandHandler = new LampVelocityCommandHandler(this).registerAll();
        for (VelocityLimboServer velocityLimboServer : limboConfig.getServers()) {
            LimboServer server = new LimboServer(velocityLimboServer.getLimboConfig(), commandHandler,
                    getClass().getClassLoader());

            ServerInfo serverInfo = new ServerInfo(velocityLimboServer.getLimboName(),
                    (InetSocketAddress) velocityLimboServer.getLimboConfig().getAddress());
            servers.put(serverInfo.getName(), server);
            this.server.registerServer(serverInfo);
            try {
                server.start();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public ProxyServer getServer() {
        return server;
    }

    public Path getDataFolder() {
        return dataFolder;
    }

    public LimboConfig getLimboConfig() {
        return limboConfig;
    }

    public Map<String, LimboServer> getServers() {
        return servers;
    }

    public static NanoLimboVelocity getInstance() {
        return instance;
    }
}
