package Commands.Cubing.Subsets.CFOP;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class OLLCMD extends Command {
    public OLLCMD(){
        this.name="oll";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getSubset(commandEvent, "oll");
    }
}
