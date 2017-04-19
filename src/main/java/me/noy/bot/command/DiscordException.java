package me.noy.bot.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Armani on 19/04/2017.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DiscordException extends Throwable {
    public final String errorMessage;
}