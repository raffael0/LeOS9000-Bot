package commands.administration.botOwner.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.IOException;

public class ShutdownCommand extends Command {
    public ShutdownCommand(){
        this.name="shutdown";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        try {
            Process p = Runtime.getRuntime().exec("pkill -f 'java -jar'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
