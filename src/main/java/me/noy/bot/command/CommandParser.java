package me.noy.bot.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Armani on 19/04/2017.
 */
public final class CommandParser {

    public CommandContainer parse(String raw, MessageReceivedEvent event) {
        List<String> split = new ArrayList<>();
        String r = raw;
        String prefix = raw.replaceFirst("!", "");
        String[] splitPrefix = prefix.split(" ");
        split.addAll(Arrays.asList(splitPrefix));
        String invoke = split.get(0);
        String[] args = new String[split.size() - 1];
        split.subList(1, split.size()).toArray(args);
        return new CommandContainer(r, prefix, splitPrefix, invoke, args, event);
    }

    @Data
    @AllArgsConstructor
    public static final class CommandContainer {
        public final String raw;
        public final String prefix;
        public final String[] splitPrefix;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;
    }

}
