package me.noy.bot.command.impl;

import lombok.SneakyThrows;
import me.noy.bot.command.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Armani on 19/04/2017.
 */
// this is a joke lmfao
public final class DDosCommand implements Command {

    @Override
    @SneakyThrows
    public boolean called(String[] args, MessageReceivedEvent event) { return true; }

    @Override
    public void action(String[] s, MessageReceivedEvent event) {
        if (!s[0].contains(".")) {
            try {
                sendMessage(event,"Could not hit! Not a real target!");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (s.length == 0) {
            this.sendMessage(event, "!ddos <ip>");
            return;
        }
        sendMessage(event, "Hitting.");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String args1 = s[0];
                if (args1.equalsIgnoreCase("stop")) {
                    System.out.println("stopped");
                    return;
                }
                if (args1.equals(s[0])) {
                    try {
                        sendMessage(event, "Hitting " + args1 + " with (venet0:0 111.111.32.3): NO FLAGS are set, 40 headers + 0 data bytes");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.schedule(task, 1000, 1000 * 2);
        new Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        task.cancel();
                        timer.cancel();
                        System.out.println("cancelled");
                        sendMessage(event, "Hit " + s[0] + " with " + " 8987321BYTES of Data.");
                    }
                }, 20000
        );
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {}

    @SafeVarargs
    private final <T> void sendMessage(MessageReceivedEvent event, T... t) {
        for (T args : t) {
            event.getTextChannel().sendMessage(args.toString()).queue();
        }
    }
}