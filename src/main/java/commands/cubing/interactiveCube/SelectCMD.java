package commands.cubing.interactiveCube;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import util.CubeUtil;

public class SelectCMD extends Command {
    public SelectCMD(){
        this.name="select";
        this.help="selects the puzzle that you want to manipulate";
        this.arguments="<2-7>";
        this.category=new Category("Cubing");
    }

    @Override
    protected void execute(CommandEvent event) {
        CubeUtil u = new CubeUtil();

        if(Integer.parseInt(event.getArgs()) >=2 && Integer.parseInt(event.getArgs()) <=7 && u.hasCube(event.getAuthor().getId(), event.getArgs())){
            u.setSelection(event.getAuthor().getId(), event.getArgs());
            event.reply("Selected your " + event.getArgs() + "x" + event.getArgs() + " cube!");
        } else if(!u.hasCube(event.getAuthor().getId(), event.getArgs()) && Integer.parseInt(event.getArgs()) >=2 && Integer.parseInt(event.getArgs()) <=7) {
            event.reply("Error, you dont have a " + event.getArgs() + "x" + event.getArgs() + " cube!");
        } else
            event.reply("Error, invalid selection!");
    }
}
