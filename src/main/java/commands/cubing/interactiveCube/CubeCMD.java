package commands.cubing.interactiveCube;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import util.BotUtil;
import util.CubeUtil;

import java.awt.*;

public class CubeCMD extends Command {
    public CubeCMD(){
        this.name="cube";
        this.arguments="<help/info>";
        this.help="help displays a tutorial for the cube commands, info returns a list of your current cubes and some info for them";
        this.category=new Category("Cubing");
    }

    @Override
    protected void execute(CommandEvent event) {
        CubeUtil u = new CubeUtil();
        EmbedBuilder eb = new EmbedBuilder();

        if(event.getArgs().equals("help")){
            eb.setTitle("Interactive Cube Commands");
            eb.setDescription("The following commands can be used to spawn interactive cubes. Use `" + BotUtil.getPrefix() + "cube[number] start` to spawn a cube. after you have spawned it, you can select it with" +
                    "`" + BotUtil.getPrefix() + "select [2|3]`, and then manipulate it using `" + BotUtil.getPrefix() + "move [your moves]`. To reset your cube use `" + BotUtil.getPrefix() + "cube[number] reset`. " +
                    "For additional help, please join the support server. You can get an invite using " + BotUtil.getPrefix() + "invite");
            eb.addField("2x2", BotUtil.getPrefix() + "cube2", true);
            eb.addField("3x3", BotUtil.getPrefix() + "cube3", true);
            eb.addField("Important", "cubes bigger than 3x3 are currently disabled and will be added back soon.", true);
            eb.setColor(Color.RED);
            event.reply(eb.build());
        } else if(event.getArgs().equals("info")){
            eb.setTitle("Your Cubes");
            String[] cubes = u.getCubeArray(event.getAuthor().getId());
            if(cubes.length!=0) {
                int pos = 0;
                for (int i = 0; i < cubes.length; i++) {
                    if (!cubes[i].equals("selection")) {
                        eb.addField(cubes[i] + "x" + cubes[i], u.getUserScramble(event.getAuthor().getId(), cubes[i]), true);
                    } else
                        pos = i;
                }
                eb.addField("current selection", u.getUserScramble(event.getAuthor().getId(), cubes[pos]), false);
                eb.setColor(Color.RED);
                event.reply(eb.build());
            } else
                event.reply("Error, you do not have any cubes at the moment!");
        } else
            event.reply("Error, invalid arguments. Try using `" + BotUtil.getPrefix() + "cube info` or `" + BotUtil.getPrefix() + "cube help` instead");

    }
}
