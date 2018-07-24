package Commands.Cubing.Subsets.Roux;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class F2BCMD extends Command {
    public F2BCMD(){
        this.name="f2b";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getSubset(commandEvent, "f2b");
    }
}
