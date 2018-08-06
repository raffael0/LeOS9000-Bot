package commands.cubing.subsets.cfop;

import util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class F2LCMD extends Command {
    public F2LCMD(){
        this.name="f2l";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getSubset(commandEvent, "f2l");
    }
}
