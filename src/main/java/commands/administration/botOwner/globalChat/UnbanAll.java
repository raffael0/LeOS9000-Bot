package commands.administration.botOwner.globalChat;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.FileWriter;
import java.io.IOException;

public class UnbanAll extends Command {
    public UnbanAll(){
        this.name="gsua";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/main/resources/globalBanned.json");
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
        event.reactSuccess();
    }
}
