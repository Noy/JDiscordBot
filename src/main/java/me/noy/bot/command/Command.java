package me.noy.bot.command;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
    boolean called(String[] args, MessageReceivedEvent event) throws DiscordException;
    void action(String[] args, MessageReceivedEvent event);
    void execute(boolean success, MessageReceivedEvent event);
}
