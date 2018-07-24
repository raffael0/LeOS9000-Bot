package Util;

import Core.Main;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.menu.Paginator;
import com.jagrosh.jdautilities.menu.Slideshow;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ScrambleImageGenerator {

    public static void getWCA(CommandEvent event, String puzzle){

        Slideshow.Builder slideshow = new Slideshow.Builder();
        slideshow.setText(puzzle + "x" + puzzle + " Scramble");
        slideshow.setEventWaiter(Main.getWaiter());
        slideshow.waitOnSinglePage(true);
        slideshow.wrapPageEnds(true);

        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("cube.leonunner.com")
                    .setPath("visualcube.php")
                    .setParameter("fmt", "png")
                    .setParameter("size", "500")
                    .setParameter("cc","black")
                    .setParameter("bg", "black")
                    .setParameter("pzl", puzzle)
                    .setParameter("sch","yogwrb")
                    .setParameter("alg", event.getArgs().replace('`', '\'').replaceAll("\\s+",""))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpGet httpget = new HttpGet(uri);
        slideshow.addItems(httpget.getURI().toString());
        uri = null;

        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("cube.leonunner.com")
                    .setPath("visualcube.php")
                    .setParameter("fmt", "png")
                    .setParameter("size", "500")
                    .setParameter("cc","black")
                    .setParameter("bg", "black")
                    .setParameter("pzl", puzzle)
                    .setParameter("sch","yogwrb")
                    .setParameter("alg", event.getArgs().replace('`', '\'').replaceAll("\\s+","")+"x2y'")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        httpget = new HttpGet(uri);
        slideshow.addItems(httpget.getURI().toString());
        Slideshow list = slideshow.build();

        list.paginate(event.getChannel(), 1);
    }

    public static void getSubset(CommandEvent event, String subset){
        Slideshow.Builder slideshow = new Slideshow.Builder();
        slideshow.setText(subset);
        slideshow.setEventWaiter(Main.getWaiter());
        slideshow.waitOnSinglePage(true);
        slideshow.wrapPageEnds(true);

        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("cube.leonunner.com")
                    .setPath("visualcube.php")
                    .setParameter("fmt", "png")
                    .setParameter("size", "500")
                    .setParameter("cc","black")
                    .setParameter("bg", "black")
                    .setParameter("stage", subset)
                    .setParameter("sch","yogwrb")
                    .setParameter("alg", event.getArgs().replace('`', '\'').replaceAll("\\s+",""))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpGet httpget = new HttpGet(uri);
        slideshow.addItems(httpget.getURI().toString());
        uri = null;

        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("cube.leonunner.com")
                    .setPath("visualcube.php")
                    .setParameter("fmt", "png")
                    .setParameter("size", "500")
                    .setParameter("cc","black")
                    .setParameter("bg", "black")
                    .setParameter("stage", subset)
                    .setParameter("sch","yogwrb")
                    .setParameter("alg", event.getArgs().replace('`', '\'').replaceAll("\\s+","")+"x2y'")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        httpget = new HttpGet(uri);
        slideshow.addItems(httpget.getURI().toString());
        Slideshow list = slideshow.build();

        list.paginate(event.getChannel(), 1);
    }
}