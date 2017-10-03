package me.noy.bot.listeners;

import me.noy.bot.Bot;
import me.noy.bot.command.CommandExecutor;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

    private static final String PREFIX = "!";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) return;
        User user = event.getAuthor();
        if (event.getMessage().getContent().equals(PREFIX)) return;
        for (String s : CommandExecutor.commands.keySet()) {
            if (!event.getMessage().getContent().startsWith(PREFIX + s)) continue;
            if (event.getMessage().getContent().startsWith(PREFIX)) {
                if (user.getName().contains("Owen") || user.getName().equalsIgnoreCase("Tom")
                        || user.getName().equalsIgnoreCase("keitho")|| user.getName().equalsIgnoreCase("pork")) {
                    CommandExecutor.handleCommand(Bot.parser.parse(event.getMessage().getContent(), event));
                } else {
                    Bot.sendMessage(event, "Sorry, You don't have permission to do that on this server!");
                }
            }
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        Bot.log("Logged in as: " + event.getJDA().getSelfUser().getName());
    }
}