package com.bivashy.limbo.command;

import com.bivashy.limbo.NanoLimboVelocity;
import com.bivashy.limbo.command.exception.SendComponentException;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import revxrsal.commands.annotation.Dependency;
import revxrsal.commands.command.CommandActor;

@Command("limbohelp")
public class HelpCommand {
    @Dependency
    private NanoLimboVelocity plugin;

    @Default
    public void execute(CommandActor actor) {
        throw new SendComponentException(plugin.getLimboConfig().getMessages().message("help"));
    }
}
