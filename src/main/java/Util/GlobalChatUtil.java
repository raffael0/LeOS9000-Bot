package Util;

import Core.Main;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GlobalChatUtil {
    private static int numberOfServers = 0;
    private static final String file = "src/main/resources/global.json";

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
            removeFromJSON(id);
            ban(id);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        unban(id);
                    }
                });
            }).start();
        }
    }

    public static void addToJson(String id, String name){
        JSONObject obj = getJSON(file);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("votes", 0);
        JSONObject votedOn = new JSONObject();
        jsonObject.put("votedOn", votedOn);
        obj.put(id, jsonObject);
        write(obj, file);
    }

    private static String getKey(String id, String key){
        JSONObject obj = (JSONObject) getJSON(file).get(id);
        return obj.get(key).toString();
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

    public static String getServerName(String id){
        JSONObject obj = getJSON(file);
        return getKey(id, "name").replaceAll("@everyone","@\u200B\u200Beveryone").replaceAll("@here","@\u200B\u200Bhere");
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

    public static boolean isConnected(String id){
        return getJSON(file).containsKey(id);
    }

    public static String[] getIdArray(){
        Object[] objects = getJSON(file).keySet().toArray();
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

    public static int getVotes(String id){
        return Integer.parseInt(getKey(id, "votes"));
    }

    public static void addVote(String serverToKick, String id){
        JSONObject json = (JSONObject) getJSON(file).get(serverToKick);
        int votes = getVotes(serverToKick);
        votes++;
        JSONObject voted = (JSONObject) json.get("votedOn");
        voted.put(id, "true");
        json.put("votes", votes);
        json.put("votedOn", voted);
        JSONObject obj = getJSON(file);
        obj.put(serverToKick, json);
        write(obj, file);
    }

    public static int getVotesNeeded(){
        if(getNumberOfServers() <= 4){
            return 2;
        } else if(getNumberOfServers() > 4 && getNumberOfServers() < 8){
            return 3;
        } else{
            if(getNumberOfServers()%2==0){
                return getNumberOfServers()%4;
            } else
                return getNumberOfServers()+1%4;
        }
    }

    public static boolean alreadyVoted(String serverToVoteOn, String id){
        JSONObject obj = (JSONObject) getJSON(file).get(serverToVoteOn);
        JSONObject jsonObject = (JSONObject) obj.get("votedOn");
        return (jsonObject.containsKey(id) ? true : false);
    }

    public static boolean banned(String id){
        JSONObject json = getJSON("src/main/resources/globalBanned.json");
        return json.containsKey(id);
    }

    private static void ban(String id){
        JSONObject obj = getJSON("src/main/resources/globalBanned.json");
        obj.put(id, "banned");
        write(obj, "src/main/resources/globalBanned.json");
    }

    private static void unban(String id){
        JSONObject obj = getJSON("src/main/resources/globalBanned.json");
        obj.remove(id);
        write(obj, "src/main/resources/globalBanned.json");
    }

}