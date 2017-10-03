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

public class SetNick implements Command {
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
        if (!selfMember.hasPermission(Permission.NICKNAME_MANAGE)) {
            Bot.sendMessage(event, "Sorry! I don't have permission to mute members in this server!");
            return;
        }
        for (User user : mentionedUsers) {
            Member member = guild.getMember(user);
            if (!selfMember.canInteract(member)) {
                Bot.sendMessage(event, "Cannot do that to member: " + member.getEffectiveName() + ", they are higher " +
                        "in the hierachy than I am!");
                continue;
            }
            StringBuilder getAllArgs = new StringBuilder();
            for (int i = 1; i != args.length; i++) {
                getAllArgs.append(args[i]).append(" ");
            }
            getAllArgs.subSequence(0, getAllArgs.length());
            guild.getController().setNickname(member, getAllArgs.toString()).queue(
                    success -> Bot.sendMessage(event, "Set the nick of " + member.getEffectiveName() + " to " + getAllArgs), error -> {
                        if (error instanceof PermissionException) {
                            Bot.sendMessage(event, "PermissionError nicking [" + member.getEffectiveName()
                                    + "]: " + error.getMessage());
                        } else {
                            Bot.sendMessage(event, "Unknown error while nicking [" + member.getEffectiveName()
                                    + "]: " + "<" + error.getClass().getSimpleName() + ">: " + error.getMessage());
                        }
                    });
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {}
}
