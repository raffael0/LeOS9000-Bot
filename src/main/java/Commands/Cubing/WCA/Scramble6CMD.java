package Commands.Cubing.WCA;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Scramble6CMD extends Command {
    public Scramble6CMD(){
        this.name="sc6";
        this.hidden=true;
        this.aliases=new String[]{"scramble6"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getWCA(commandEvent, "6");
    }
}
