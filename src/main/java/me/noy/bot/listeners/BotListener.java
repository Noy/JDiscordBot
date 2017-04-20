package me.noy.bot.listeners;

import me.noy.bot.Bot;
import me.noy.bot.command.CommandExecutor;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) return;
        User user = event.getAuthor();
        if (event.getMessage().getContent().startsWith("!")) {
            if (user.getName().contains("") || user.getName().equalsIgnoreCase("") || user.getName().equalsIgnoreCase("")) {
                CommandExecutor.handleCommand(Bot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
            } else {
                Bot.sendMessage(event, "Sorry! You don't have permission to do that on this server!");
            }
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        Bot.log("status", "Logged in as: " + event.getJDA().getSelfUser().getName());
    }
}
