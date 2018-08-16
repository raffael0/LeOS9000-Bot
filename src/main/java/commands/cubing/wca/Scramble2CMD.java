package commands.cubing.wca;

import com.leonunner.javacube.cubes.Cube2;
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
        Cube2 cube = new Cube2(commandEvent.getArgs(), false);
        cube.updateFile();
        commandEvent.reply(cube.getFile(), commandEvent.getArgs() + ".png");
    }
}
