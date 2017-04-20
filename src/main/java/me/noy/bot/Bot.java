package me.noy.bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import me.noy.bot.command.CommandExecutor;
import me.noy.bot.command.CommandParser;
import me.noy.bot.command.impl.*;
import me.noy.bot.command.impl.discord.*;
import me.noy.bot.listeners.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

@Log
public final class Bot {

    @Getter(AccessLevel.PACKAGE) private static Bot instance;
    private JDA jda;
    public static CommandParser parser = new CommandParser();

    @SneakyThrows
    private void enable() {
        instance = this;
        jda = new JDABuilder(AccountType.BOT).setToken(Hidden.TOKEN).addListener(new BotListener()).buildBlocking();
        registerAllCommands();
    }

    public static void main(String[] args) {
        Bot bot = new Bot();
        bot.enable();
    }

    @SafeVarargs
    public static <T> void log(T... t) {
        for (T args : t) {
            log.info(args.toString());
        }
    }

    @SafeVarargs
    public static <T> void sendMessage(MessageReceivedEvent event, T... t) {
        for (T args : t) {
            event.getTextChannel().sendMessage(args.toString()).queue();
        }
    }

    private void registerAllCommands() {
        CommandExecutor.commands.put("ddos", new DDosCommand());
        CommandExecutor.commands.put("wouldyou", new RandomResponses());
        CommandExecutor.commands.put("googleimg", new GoogleImageSearch());
        CommandExecutor.commands.put("google", new GoogleSearch());
        CommandExecutor.commands.put("kick", new Kick());
        CommandExecutor.commands.put("mute", new Mute());
        CommandExecutor.commands.put("help", new Help());
        CommandExecutor.commands.put("setnick", new SetNick());
        CommandExecutor.commands.put("announce", new Announcement());
        CommandExecutor.commands.put("getinfo", new GetInfo());
        CommandExecutor.commands.put("join", new JoinVoice());
    }
}