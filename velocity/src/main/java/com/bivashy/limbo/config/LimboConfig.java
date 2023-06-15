package com.bivashy.limbo.config;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import com.bivashy.limbo.NanoLimboVelocity;
import com.bivashy.limbo.config.model.VelocityLimboServer;
import com.bivashy.limbo.config.model.VelocityLimboServer.VelocityLimboServerSerializer;

import ua.nanit.limbo.server.Logger;

public class LimboConfig {
    private List<VelocityLimboServer> servers;
    private MessageConfiguration messages;
    private ConfigurationNode root;

    public LimboConfig(NanoLimboVelocity plugin) {
        try {
            root = YamlConfigurationLoader.builder()
                    .defaultOptions(ConfigurationOptions.defaults()
                            .serializers(
                                    TypeSerializerCollection.builder()
                                            .register(VelocityLimboServer.class, new VelocityLimboServerSerializer())
                                            .build()))
                    .file(ConfigurationUtil.saveDefaultConfig(plugin, "config.yml"))
                    .build().
                    load();
            servers = root.node("limbos").childrenMap().values().stream().map(node -> {
                try {
                    return node.get(VelocityLimboServer.class);
                } catch(SerializationException e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());
            messages = new MessageConfiguration(root.node("messages"));
        } catch(ConfigurateException e) {
            e.printStackTrace();
            Logger.warning("Cannot load config.yml");
        }
    }

    public MessageConfiguration getMessages() {
        return messages;
    }

    public List<VelocityLimboServer> getServers() {
        return Collections.unmodifiableList(servers);
    }
}