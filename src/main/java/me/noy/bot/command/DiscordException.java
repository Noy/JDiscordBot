package me.noy.bot.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiscordException extends Throwable {
    public final String errorMessage;
}