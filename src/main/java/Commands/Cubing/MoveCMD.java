package Commands.Cubing;

import Util.BotUtil;
import Util.CubeUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class MoveCMD extends Command {
    public MoveCMD(){
        this.name="move";
        this.help="manipulates your cube if you have one!";
        this.arguments="<your moves>";
    }

    @Override
    protected void execute(CommandEvent event) {
        CubeUtil u = new CubeUtil();

        if(!u.hasUser(event.getAuthor().getId())){
            event.reply("Error, you don't have a cube! Use `" + BotUtil.getPrefix() + "cube start` to get one!");
        } else
            u.removeMessage(event);
            u.setScramble(event.getAuthor().getId(), event.getArgs());
            u.getScramble(event, u.getUserScramble(event.getAuthor().getId()));
    }
}
