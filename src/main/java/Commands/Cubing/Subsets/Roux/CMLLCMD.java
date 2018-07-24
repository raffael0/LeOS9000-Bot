package Commands.Cubing.Subsets.Roux;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class CMLLCMD extends Command {
    public CMLLCMD(){
        this.name="cmll";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getSubset(commandEvent, "cmll");
    }
}
