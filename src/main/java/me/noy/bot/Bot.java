package me.noy.bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import me.noy.bot.command.CommandExecutor;
import me.noy.bot.command.CommandParser;
import me.noy.bot.command.impl.DDosCommand;
import me.noy.bot.listeners.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

@Log
public final class Bot {

    @Getter(AccessLevel.PACKAGE) private static Bot instance;
    private JDA jda;
    public static CommandParser parser = new CommandParser();

    @SneakyThrows
    private void enable() {
        instance = this;
        jda = new JDABuilder(AccountType.BOT).setToken(Hidden.TOKEN).addListener(new BotListener()).buildBlocking();
        CommandExecutor.commands.put("ddos", new DDosCommand());
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
}
