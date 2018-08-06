package commands.cubing.subsets.cfop;

import util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class CrossCMD extends Command {
    public CrossCMD(){
        this.name="cross";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getSubset(commandEvent, "cross");
    }
}
