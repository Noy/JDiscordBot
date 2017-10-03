package me.noy.bot.command.impl;

import lombok.SneakyThrows;
import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

// this is a joke lmfao
public final class DDosCommand implements Command {

    @Override
    @SneakyThrows
    public boolean called(String[] args, MessageReceivedEvent event) { return true; }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length == 0) {
            Bot.sendMessage(event, "!ddos <ip>");
            return;
        }
        if (!args[0].contains(".")) {
            Bot.sendMessage(event,"Could not hit! Not a real target!");
            return;
        }
        Bot.sendMessage(event, "Hitting.");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String args1 = args[0];
                if (args1.equalsIgnoreCase("stop")) {
                    System.out.println("stopped");
                    return;
                }
                if (args1.equals(args[0])) {
                    try {
                        Bot.sendMessage(event, "Hitting " + args1 + " with (venet0:0 111.111.32.3): NO FLAGS are set, 40 headers + 0 data bytes");
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
                        Random rand = new Random();
                        int randomNum = rand.nextInt(29238932);
                        Bot.sendMessage(event, "Hit " + args[0] + " with " + randomNum + " Bytes of Data.");
                    }
                }, 20000
        );
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {}
}
