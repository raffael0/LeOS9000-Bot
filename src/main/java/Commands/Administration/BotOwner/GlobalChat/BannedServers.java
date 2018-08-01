package Commands.Administration.BotOwner.GlobalChat;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.MessageBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BannedServers extends Command {
    public BannedServers(){
        this.name="gsbl";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(new FileReader("src/main/resources/globalBanned.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Object[] objects = obj.keySet().toArray();
        String[] arr = Arrays.copyOf(objects, objects.length, String[].class);

        MessageBuilder mb = new MessageBuilder();
        for(int i = 0; i<arr.length; i++){
            mb.append(arr[i] + "\n");
        }
        event.reply(mb.build());
    }
}
