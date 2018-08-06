package commands.cubing.interactiveCube.sizes;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import util.BotUtil;
import util.CubeUtil;

public class InteractiveCube7 extends Command {
    public InteractiveCube7(){
        this.name="cube7";
        this.help="summons an interactive cube that you can manipulate!";
        this.arguments="<start/reset/stop>";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        CubeUtil u = new CubeUtil();
        String id = event.getAuthor().getId();

        u.removeMessage(event);

        if(!u.hasUser(id)){
            u.addUser(id);
        }

        if(event.getArgs().isEmpty()){
            if(u.hasCube(event.getAuthor().getId(), "7")){
                u.getScramble(event, u.getUserScramble(id, "7"), "7");
            } else
                event.reply("Error, you do not have a 7x7 cube at the moment! Type `" + BotUtil.getPrefix() +"cube7 start` to get one!");
        } else if(event.getArgs().equals("reset") && u.hasUser(id)){
            u.clearScramble(id, "7");
            event.reply("Successfully reset your cube!");
        } else if(event.getArgs().equals("start")){
            if(u.hasCube(id, "7")){
                event.reply("Error, you already have a cube!");
            } else {
                u.addCube(id, "7");
                event.reply("Successfully created a cube! First select the cube with `" + BotUtil.getPrefix() + "select 7`, then use " + BotUtil.getPrefix() + "move [your moves] to manipulate it!");
            }
        } else if(event.getArgs().equals("stop")){
            if(!u.hasCube(id, "7")){
                event.reply("Error, you don't have a 7x7 cube!");
            } else
                u.removeCube(id, "7");
            event.reply("Successfully removed your cube!");
        }
    }
}
