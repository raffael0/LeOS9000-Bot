package Commands.Cubing.WCA;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Scramble4CMD extends Command {
    public Scramble4CMD(){
        this.name="sc4";
        this.hidden=true;
        this.aliases=new String[]{"scramble4"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getWCA(commandEvent, "4");
    }
}
