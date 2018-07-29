package Util;

import Core.Main;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.events.Event;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class GlobalChatUtil {
    public static void main(String[] args) {
        String[] arr = getIdArray();
        for(int i = 0; i<arr.length; i++){
            System.out.println(arr[i] + " " + getServerName(arr[i]));
        }
    }

    private static int numberOfServers = 0;

    public static int getNumberOfServers(){
        return numberOfServers;
    }

    public static void addServer(String id, String name){
        numberOfServers++;
        addToJson(id, name);
        sendToAllServers("```\"" + name + "\" (" + id + ") just joined the global chat```", id);
    }

    public static void removeServer(String id, boolean kick){
        numberOfServers--;
        if(!kick) {
            sendToAllServers("```\"" + getServerName(id) + "\" (" + id + ") just left the global chat```", id);
        } else{
            sendToAllServers("```\"" + getServerName(id) + "\" (" + id + ") was kicked from the global chat```", id);
        }
        removeFromJSON(id);
    }

    private static void addToJson(String id, String name){
        JSONObject obj = getJSON();
        obj.put(id, name);
        write(obj);
    }

    private static void removeFromJSON(String id){
        JSONObject obj = getJSON();
        obj.remove(id);
        write(obj);
    }

    private static void write(JSONObject object) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/main/resources/global.json");
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
        return obj.get(id).toString().replaceAll("@everyone","@\u200B\u200Beveryone").replaceAll("@here","@\u200B\u200Bhere");
    }

    private static JSONObject getJSON(){
        JSONParser parser = new JSONParser();
        Object tempObj = null;
        try {
            tempObj = parser.parse(new FileReader("src/main/resources/global.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject) tempObj;
    }

    public static boolean isConnected(String id){
        return getJSON().containsKey(id);
    }

    public static String[] getIdArray(){
        Object[] objects = getJSON().keySet().toArray();
        return Arrays.copyOf(objects, objects.length, String[].class);
    }

    public static void sendToAllServers(String content, String leaveOut){
        String[] arr = getIdArray();

        for(int i = 0; i<arr.length; i++){
            if(!arr[i].equals(leaveOut)) {
                Main.getJDA().getGuildById(arr[i]).getTextChannelsByName("global", true).get(0).sendMessage(content).queue();
            }
        }
    }
}
