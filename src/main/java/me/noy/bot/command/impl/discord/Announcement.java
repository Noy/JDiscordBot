package me.noy.bot.command.impl.discord;

import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public final class Announcement implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        List<TextChannel> textChannels = guild.getTextChannels();
        StringBuilder getAllArgs = new StringBuilder();
        for (String s : args) {
            getAllArgs.append(s).append(" ");
        }
        getAllArgs.substring(0, getAllArgs.length() - 1);
        for (TextChannel textChannel : textChannels) {
            if (textChannel.canTalk()) {
                textChannel.sendMessage(getAllArgs.toString()).queue();
            }
        }
        if (guild.getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            event.getMessage().deleteMessage().queue();
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {}
}