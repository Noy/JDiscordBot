package me.noy.bot.command.impl;

import lombok.SneakyThrows;
import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class GoogleSearch implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) throws DiscordException { return true; }

    @Override
    @SneakyThrows
    public void action(String[] args, MessageReceivedEvent event) {
        StringBuilder getAllArgs = new StringBuilder();
        for (String s : args) {
            getAllArgs.append(s).append(" ");
        }
        getAllArgs.substring(0, getAllArgs.length() - 1);
        Bot.sendMessage(event, event.getAuthor().getAsMention() + ", looking..");
        Bot.sendMessage(event, "Request Received! Searching for: " + getAllArgs.toString());
        String google = "http://www.google.com/search?q=";
        String search = getAllArgs.toString();
        String charset = "UTF-8";
        String param ="";
        String userAgent = "Discord 1.0 (+http://example.com/bot)";
        Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)+param).userAgent(userAgent).get().select(".g>.r>a");
        //List<String> titles = links.stream().map(Element::text).collect(Collectors.toList());
        for (Element link : links) {
            link = links.get((int) (Math.random() * links.size()));
            String title = link.text();
            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
            Bot.sendMessage(event, title+"\n"+url);
            break;
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) { }
}
