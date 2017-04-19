package me.noy.bot.command.impl;

import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class RandomResponses implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException { return true; }

    private String[] msg = {"Yes", "No", "Of course", "Ew, no", ":nauseated_face:", "YES", "Oh my god yes", "I wish", "Uh, no", "/me cringes", "/tableflip", "/shrug"};

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length == 0) {
            Bot.sendMessage(event, "Who??");
            return;
        }
        Bot.sendMessage(event,msg[new Random().nextInt(msg.length)] + ".");
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {

    }
}
