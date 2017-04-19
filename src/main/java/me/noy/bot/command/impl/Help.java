package me.noy.bot.command.impl;

import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Help implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Bot.sendMessage(event, "---Introduction---");
        Bot.sendMessage(event, "Discord Bot made by N.");
        Bot.sendMessage(event, "Every command starts with a '!'");
        Bot.sendMessage(event, "Some commands are !kick , !google , !topic , etc.. ");
        Bot.sendMessage(event, "For all commands, visit this Gist: ");
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {

    }
}
