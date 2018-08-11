package commands.cubing;

import core.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class ScrambleCMD extends Command {
    public ScrambleCMD(){
        this.name="drawscramble";
        this.aliases = new String[]{"scramble", "scramblehelp", "schelp"};
        this.help="returns an image of your scramble";
        this.category=new Category("Cubing");
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Scramble Command");
        eb.setThumbnail(Main.getJDA().getSelfUser().getAvatarUrl());
        eb.setDescription("Use one of the following commands to an image of a given scramble. Put your scramble after the command, like this: \n\n-sc3 R U R' U'\n\nDifferent bot.commands:");
        eb.addField("wca", "sc2 - 2x2\nsc3 - 3x3", true);
        eb.addField("Important, ","Cubes bigger than 3x3 are currently disabled. They will be back soon", false);
        eb.setFooter("images are generated using JavaCube!", event.getSelfUser().getAvatarUrl());

        event.reply(eb.build());
    }
}
