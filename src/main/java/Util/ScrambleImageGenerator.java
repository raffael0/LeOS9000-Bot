package Util;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class ScrambleImageGenerator {
    public static void getWCA(CommandEvent event, String puzzle){
        EmbedBuilder eb = new EmbedBuilder();

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
        eb.setImage(httpget.getURI().toString());
        eb.setTitle(puzzle + "x" + puzzle + " Scramble");
        System.out.println(httpget.getURI().toString());
        eb.setDescription(event.getArgs());
        event.reply(eb.build());

        uri = null;
        eb.clear();

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
        eb.setImage(httpget.getURI().toString());
        eb.setFooter("images generated using VisualCube", "http://cube.crider.co.uk/visualcube_4.gif");
        event.reply(eb.build());
    }

    public static void getSubset(CommandEvent event, String subset){
        EmbedBuilder eb = new EmbedBuilder();

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
        eb.setImage(httpget.getURI().toString());
        eb.setTitle(subset);
        eb.setDescription(event.getArgs());
        event.reply(eb.build());

        uri = null;
        eb.clear();

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
        eb.setImage(httpget.getURI().toString());
        eb.setFooter("images generated using VisualCube", "http://cube.crider.co.uk/visualcube_4.gif");
        event.reply(eb.build());
    }
}