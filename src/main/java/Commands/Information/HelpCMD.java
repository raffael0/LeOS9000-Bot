package Commands.Information;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class HelpCMD extends Command {
    public HelpCMD(){
        this.name="help";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("");
    }
}
