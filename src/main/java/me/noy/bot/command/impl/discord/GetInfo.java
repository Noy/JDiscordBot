package me.noy.bot.command.impl.discord;

import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public final class GetInfo implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        List<User> mentionedUsers = event.getMessage().getMentionedUsers();
        for (User user : mentionedUsers) {
            Member member = guild.getMember(user);
            Bot.sendMessage(event, "Their Display name is: " + member.getEffectiveName());
            Bot.sendMessage(event, "They joined discord in " + member.getJoinDate().getMonth() + " " + member.getJoinDate().getYear());
            Bot.sendMessage(event, "Their roles are: " +  member.getRoles());
            Bot.sendMessage(event, "Discord username is: " + member.getUser().getName());
            Bot.sendMessage(event, "Discord ID is: " + member.getUser().getId());
            Bot.sendMessage(event, "Their online status is: " + member.getOnlineStatus());
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {

    }
}