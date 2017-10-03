package me.noy.bot.command.impl.discord;

import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

import java.util.List;

public class Mute implements Command {
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
        if (!selfMember.hasPermission(Permission.VOICE_MUTE_OTHERS)) {
            Bot.sendMessage(event,"Sorry! I don't have permission to mute members in this server!");
            return;
        }
        for (User user : mentionedUsers) {
            Member member = guild.getMember(user);
            if (!selfMember.canInteract(member)) {
                Bot.sendMessage(event, "Cannot mute member: " + member.getEffectiveName() + ", they are higher " +
                        "in the hierachy than I am!");
                continue;
            }
            guild.getController().setMute(member, true).queue(
                    success -> Bot.sendMessage(event,"Muted " + member.getEffectiveName() + "."), error -> {
                        if (error instanceof PermissionException) {
                            PermissionException pe = (PermissionException) error;
                            Permission missingPermission = pe.getPermission();
                            Bot.sendMessage(event, "PermissionError muting [" + member.getEffectiveName()
                                    + "]: " + error.getMessage());
                        } else {
                            Bot.sendMessage(event, "Unknown error while muting [" + member.getEffectiveName()
                                    + "]: " + "<" + error.getClass().getSimpleName() + ">: " + error.getMessage());
                        }
                    });
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {

    }
}
