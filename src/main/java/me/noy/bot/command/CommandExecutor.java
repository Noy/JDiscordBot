package me.noy.bot.command;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public final class CommandExecutor {
    public static Map<String, Command> commands = new HashMap<>();

    @SneakyThrows
    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if (commands.containsKey(cmd.invoke)) {
            boolean bool = commands.get(cmd.invoke).called(cmd.args, cmd.event);
            if (commands.get(cmd.invoke).called(cmd.args, cmd.event)) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).execute(bool, cmd.event);
            } else {
                commands.get(cmd.invoke).execute(bool, cmd.event);
            }
        }
    }
}
