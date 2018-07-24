package Commands.Cubing.WCA;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Scramble7CMD extends Command {
    public Scramble7CMD(){
        this.name="sc7";
        this.hidden=true;
        this.aliases=new String[]{"scramble7"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getWCA(commandEvent, "7");
    }
}
