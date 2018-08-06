package commands.cubing.interactiveCube;

import util.BotUtil;
import util.CubeUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class MoveCMD extends Command {
    public MoveCMD(){
        this.name="move";
        this.help="manipulates your cube if you have one!";
        this.arguments="<your moves>";
        this.category=new Category("Cubing");
    }

    @Override
    protected void execute(CommandEvent event) {
        CubeUtil u = new CubeUtil();

        if(!u.hasUser(event.getAuthor().getId())){
            event.reply("Error, you don't have a cube! Use `" + BotUtil.getPrefix() + "cube start` to get one!");
        } else if(!u.hasChoice(event.getAuthor().getId())){
            event.reply("Error, you have to choose a cube first!");
        } else
            u.removeMessage(event);
            u.setScramble(event.getAuthor().getId(), event.getArgs(), u.getSelection(event.getAuthor().getId()));
            u.getScramble(event, u.getUserScramble(event.getAuthor().getId(), u.getSelection(event.getAuthor().getId())), u.getSelection(event.getAuthor().getId()));
    }
}
