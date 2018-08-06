package util;

import core.Main;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.menu.Slideshow;
import net.dv8tion.jda.core.entities.Message;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class CubeUtil {
    private static final String file = "src/main/resources/cube.json";

    public static void addUser(String id){
        addToJson(id);
    }

    public static void removeUser(String id){
        removeFromJSON(id);
    }

    public static boolean hasUser(String id){
        return getJSON(file).containsKey(id);
    }

    public static boolean hasCube(String id, String size){
        JSONObject obj = (JSONObject) getJSON(file).get(id);
        return obj.containsKey(size);
    }

    public static void addCube(String id, String size){
        JSONObject obj =  (JSONObject) getJSON(file).get(id);
        obj.put(size, "");
        JSONObject object = getJSON(file);
        object.put(id, obj);
        write(object, file);
    }

    public static void removeCube(String id, String size){
        JSONObject obj =  (JSONObject) getJSON(file).get(id);
        obj.remove(size);
        JSONObject object = getJSON(file);
        object.put(id, obj);
        write(object, file);
    }

    public static String[] getCubeArray(String id){
            Object[] objects = ((JSONObject) getJSON(file).get(id)).keySet().toArray();
            return Arrays.copyOf(objects, objects.length, String[].class);
    }

    public static boolean hasChoice(String id){
        JSONObject obj = (JSONObject) getJSON(file).get(id);
        return obj.containsKey("selection");
    }

    public static void setScramble(String id, String scramble, String size){
        JSONObject obj = (JSONObject) getJSON(file).get(id);
        String existingScramble = getUserScramble(id, size) + " " + scramble;
        obj.put(size, existingScramble);
        JSONObject object = getJSON(file);
        object.put(id, obj);
        write(object, file);
    }

    public static void setSelection(String id, String selection){
        JSONObject obj =  (JSONObject) getJSON(file).get(id);
        obj.put("selection", selection);
        JSONObject object = getJSON(file);
        object.put(id, obj);
        write(object, file);
    }

    public static String getSelection(String id){
        JSONObject obj = (JSONObject) getJSON(file).get(id);
        return obj.get("selection").toString();
    }

    public static void clearScramble(String id, String size){
        JSONObject obj =  (JSONObject) getJSON(file).get(id);
        obj.put(size, "");
        JSONObject object = getJSON(file);
        object.put(id, obj);
        write(object, file);
    }

    public static void removeMessage(CommandEvent event){
        int count = 0;
        for(Message message : event.getChannel().getIterableHistory().cache(false)){
            if(count < 50) {
                if (message.getContentRaw().contains(event.getAuthor().getName()) && message.getAuthor().isBot()) {
                    message.delete().queue();
                    break;
                }
                count++;
            } else {
                break;
            }
        }
    }

    public static void getScramble(CommandEvent event, String scramble, String size){
            Slideshow.Builder slideshow = new Slideshow.Builder();
            slideshow.setText(event.getAuthor().getName() + "'s cube. scramble: " + scramble);
            slideshow.setEventWaiter(Main.getWaiter());
            slideshow.waitOnSinglePage(true);
            slideshow.wrapPageEnds(true);
            slideshow.setUsers(event.getAuthor());

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
                        .setParameter("pzl", size)
                        .setParameter("sch","wrgyob")
                        .setParameter("alg", scramble.replace('`', '\'').replaceAll("\\s+",""))
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
                        .setParameter("pzl", size)
                        .setParameter("sch","wrgyob")
                        .setParameter("alg", scramble.replace('`', '\'').replaceAll("\\s+","")+"x2y'")
                        .build();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            httpget = new HttpGet(uri);
            slideshow.addItems(httpget.getURI().toString());
            Slideshow list = slideshow.build();
            list.paginate(event.getChannel(), 1);
    }

    public static String getUserScramble(String id, String size){
        JSONObject obj = (JSONObject) getJSON(file).get(id);
        return obj.get(size).toString();
    }

    private static void addToJson(String id){
        JSONObject obj = getJSON(file);
        JSONObject details = new JSONObject();
        obj.put(id, details);
        write(obj, file);
    }

    private static void removeFromJSON(String id){
        JSONObject obj = getJSON(file);
        obj.remove(id);
        write(obj, file);
    }

    private static void write(JSONObject object, String path) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.write(object.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getJSON(String path){
        JSONParser parser = new JSONParser();
        Object tempObj = null;
        try {
            tempObj = parser.parse(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) tempObj;
    }

}
