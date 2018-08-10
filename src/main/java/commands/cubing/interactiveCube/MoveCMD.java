package commands.cubing.interactiveCube;

import com.leonunner.javacube.cubes.Cube2;
import com.leonunner.javacube.cubes.Cube3;
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
        String id = event.getAuthor().getId();

        if(!u.hasUser(event.getAuthor().getId())){
            event.reply("Error, you don't have a cube! Use `" + BotUtil.getPrefix() + "cube start` to get one!");
        } else if(!u.hasChoice(event.getAuthor().getId())){
            event.reply("Error, you have to choose a cube first!");
        } else
            u.removeMessage(event);
            u.setScramble(event.getAuthor().getId(), event.getArgs(), u.getSelection(event.getAuthor().getId()));

            if(u.hasCube(id, "3") &&u.getSelection(event.getAuthor().getId()).equals("3")){
                Cube3 cube3 = new Cube3(u.getUserScramble(id, "3"), false);
                event.reply(cube3.getFile(), "scramble3-" + event.getAuthor().getName() + ".png");
            } else if(u.hasCube(id, "2") && u.getSelection(event.getAuthor().getId()).equals("2")){
                Cube2 cube2 = new Cube2(u.getUserScramble(id, "2"), false);
                event.reply(cube2.getFile(), "scramble2-" + event.getAuthor().getName() + ".png");
            }
    }
}
