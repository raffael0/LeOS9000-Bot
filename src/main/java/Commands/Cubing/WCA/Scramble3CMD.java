package Commands.Cubing.WCA;

import Util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Scramble3CMD extends Command {
    public Scramble3CMD(){
        this.name="sc3";
        this.hidden=true;
        this.aliases=new String[]{"scramble3", "sc"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getWCA(commandEvent, "3");
    }
}
