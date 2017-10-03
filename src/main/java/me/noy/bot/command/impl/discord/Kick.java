package me.noy.bot.command.impl.discord;

import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class Kick implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        List<User> mentionedUsers = event.getMessage().getMentionedUsers();
        if (args.length == 0) return;
        Guild guild = event.getGuild();
        Member selfMember = guild.getSelfMember();
        if (!selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            Bot.sendMessage(event,"Sorry! I don't have permission to kick members in this server!");
            return;
        }
        for (User user : mentionedUsers) {
            Member member = guild.getMember(user);
            if (!selfMember.canInteract(member)) {
                Bot.sendMessage(event, "Cannot mute member: " + member.getEffectiveName() + ", they are higher " +
                        "in the hierarchy than I am!");
                continue;
            }
            guild.getController().kick(member);
            Bot.sendMessage(event, "Kicked " + member);
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {

    }
}
