package me.noy.bot.command.impl;

import lombok.SneakyThrows;
import me.noy.bot.Bot;
import me.noy.bot.command.Command;
import me.noy.bot.command.DiscordException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;

public final class GoogleImageSearch implements Command {

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
        String google = "http://www.google.com/search?tbm=isch&q=";
        String search = getAllArgs.toString();
        String charset = "UTF-8";
        String param = "&source=web&sa=X&ved=0ahUKEwiEr5rZrMLSAhXM8YMKHRVzCJcQ_AUICCgB&biw=1440&bih=799#imgrc=XWXPqrX1RFJiaM:";
        String userAgent = "Discord 1.0 (+http://example.com/bot)";
        System.out.println(Jsoup.connect(google + URLEncoder.encode(search, charset) + param).followRedirects(true).userAgent(userAgent).timeout(200000).get());
        Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset) + param).followRedirects(true).userAgent(userAgent).timeout(200000).get().select("#ires td a img");
        for (Element link : links) {
            link = links.get((int) (Math.random() * links.size()));
            String url = link.absUrl("src");
            System.out.println(url);
            BufferedImage image = ImageIO.read(new URL(url).openStream());
            ImageIO.write(image, "PNG", new File("search.png"));
            Bot.sendMessage(event, url);
            Files.delete(new File("search.png").toPath());
            break;
        }
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) { }
}
