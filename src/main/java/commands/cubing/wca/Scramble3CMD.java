package commands.cubing.wca;

import com.leonunner.javacube.cubes.Cube3;
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
        Cube3 cube = new Cube3(commandEvent.getArgs(), false);
        cube.updateFile();
        commandEvent.reply(cube.getFile(), commandEvent.getArgs() + ".png");
    }
}
