package me.noy.bot.command.impl.discord;

import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

// this still needs work
public class JoinVoice implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args.length == 0) return;
        VoiceChannel channel = event.getMessage().getGuild().getVoiceChannels().stream().filter(voiceChannel
                -> voiceChannel.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);

        System.out.println(channel);
        if (channel == null) {
            Bot.sendMessage(event, "No voice channel available to join with that name. Check the spelling!");
            return;
        }
        connectTo(channel);
        Bot.sendMessage(event, "Joining " + args[0]);
    }

    private void connectTo(VoiceChannel channel) {
        AudioManager manager = channel.getGuild().getAudioManager();
        manager.openAudioConnection(channel);
    }


    @Override
    public void execute(boolean success, MessageReceivedEvent event) {}
}
