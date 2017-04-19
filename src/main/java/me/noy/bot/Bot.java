package me.noy.bot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import me.noy.bot.listeners.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;


public class Bot {

    @Getter(AccessLevel.PACKAGE) private static Bot instance;
    private JDA jda;
    private static final String TOKEN = "<HIDDEN>";


    @SneakyThrows
    private void enable() {
        instance = this;
        jda = new JDABuilder(AccountType.BOT).setToken(TOKEN).addListener(new ReadyListener()).addListener(new BotListener()).buildBlocking();
        jda.addEventListener(new MessageListener());
    }

    public static void main(String[] args) {
        Bot bot = new Bot();
        bot.enable();
    }

}
