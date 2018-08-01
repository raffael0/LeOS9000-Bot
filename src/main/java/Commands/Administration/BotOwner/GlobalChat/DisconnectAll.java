package Commands.Administration.BotOwner.GlobalChat;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.FileWriter;
import java.io.IOException;

public class DisconnectAll extends Command {
    public DisconnectAll(){
        this.name="gsda";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/main/resources/global.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.write("{}");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
