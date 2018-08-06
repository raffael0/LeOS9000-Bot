package commands.cubing.wca;

import util.ScrambleImageGenerator;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Scramble5CMD extends Command {
    public Scramble5CMD(){
        this.name="sc5";
        this.hidden=true;
        this.aliases=new String[]{"scramble5"};
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        ScrambleImageGenerator.getWCA(commandEvent, "5");
    }
}
