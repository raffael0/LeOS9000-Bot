package commands.cubing.wca;

import util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Scramble2CMD extends Command {
    public Scramble2CMD(){
        this.name="sc2";
        this.hidden=true;
        this.aliases=new String[]{"scramble2"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getWCA(commandEvent, "2");
    }
}
