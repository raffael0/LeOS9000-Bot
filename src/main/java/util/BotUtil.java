package util;
import net.dv8tion.jda.core.entities.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class BotUtil {
    //TODO:add banned users in JSON
    private static String[] banned = {"346295434546774016"};

    public static boolean isAllowed(Message message){
        for(String s : banned){
            if(message.getAuthor().getId().equals(s)){
                return false;
            }
        }
        return true;
    }

    static JSONObject getJSON(){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try
        {
            Object obj = parser.parse(new FileReader("src/main/resources/config.json"));
            jsonObject = (JSONObject) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getToken(){
        return (String) getJSON().get("Token");
    }

    public static String getServer(){
        return (String) getJSON().get("Server");
    }

    public static String getChannel(){
        return (String) getJSON().get("Channel");
    }

    public static String getOwner(){
        return (String) getJSON().get("Owner");
    }

    public static String getStatus(){
        return (String) getJSON().get("Status");
    }

    public static String getPrefix(){
        return (String) getJSON().get("Prefix");
    }

    public static void set(String key, String value){
        JSONObject obj = getJSON();
        obj.put(key, value);
        try {
            FileWriter fw = new FileWriter("src/main/resources/config.json");
            fw.write(obj.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPrefix(String value){
        JSONObject obj = getJSON();
        System.out.println(obj.toString());
        obj.put("Prefix", value);
        System.out.println(obj.toJSONString());
        try {
            FileWriter fw = new FileWriter("src/main/resources/config.json");
            fw.write(obj.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("changed prefix to " + value + "\nGetter: " + getPrefix());
    }

    public static void reboot(){
        try {
            Process p = Runtime.getRuntime().exec("src/main/resources/restart.sh");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(){
        try {
            Process p = Runtime.getRuntime().exec("src/main/resources/script.sh");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
