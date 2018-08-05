package Commands.Cubing;

import Util.BotUtil;
import Util.CubeUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class InteractiveCube extends Command {

    public InteractiveCube(){
        this.name="cube";
        this.help="summons an interactive cube that you can manipulate!";
        this.arguments="<start/reset/stop>";
    }

    @Override
    protected void execute(CommandEvent event) {
        CubeUtil u = new CubeUtil();
        String id = event.getAuthor().getId();

        u.removeMessage(event);
        if(event.getArgs().isEmpty()){
            if(u.hasUser(event.getAuthor().getId())){
                u.getScramble(event, u.getUserScramble(id));
            } else
                event.reply("Error, you do not have a cube at the moment! Type -cube start to get one!");
        } else if(event.getArgs().equals("reset") && u.hasUser(id)){
            u.clearScramble(id);
            event.reply("Successfully reset your cube!");
        } else if(event.getArgs().equals("start")){
            if(u.hasUser(id)){
                event.reply("Error, you already have a cube!");
            } else
                u.addUser(id);
                event.reply("Successfully created a cube! Use " + BotUtil.getPrefix() + "move [your moves] to manipulate it!");
        } else if(event.getArgs().equals("stop")){
            if(!u.hasUser(id)){
                event.reply("Error, you don't have a cube!");
            } else
                u.removeUser(id);
                 event.reply("Successfully removed your cube!");
        }
    }
}
