package Util;

import Core.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class CubingCMDUtil {

    public static void setFalse(String id){
        JSONObject obj = getJSON();
        obj.put(id, "off");
        write(obj);
    }

    public static void setTrue(String id){
        JSONObject obj = getJSON();
        obj.put(id, "on");
        write(obj);
    }

    public static String getStatus(String id){
        return getJSON().get(id).toString();
    }

    public static boolean exists(String id){
        return getJSON().containsKey(id);
    }

    private static void write(JSONObject object) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/main/resources/cubing.json");
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

    public static String getServerName(String id){
        JSONObject obj = getJSON();
        return obj.get(id).toString();
    }

    private static JSONObject getJSON(){
        JSONParser parser = new JSONParser();
        Object tempObj = null;
        try {
            tempObj = parser.parse(new FileReader("src/main/resources/cubing.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) tempObj;
    }
}
