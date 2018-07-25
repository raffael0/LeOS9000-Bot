package Commands.Cubing;

import Core.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class ScrambleCMD extends Command {
    public ScrambleCMD(){
        this.name="drawscramble";
        this.aliases = new String[]{"scramble", "scramblehelp", "schelp"};
        this.help="returns an image of your scramble";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Scramble Command");
        eb.setThumbnail(Main.getJDA().getSelfUser().getAvatarUrl());
        eb.setDescription("Use one of the following commands to an image of a given scramble. Put your scramble after the command, like this: \n\n.sc3 R U R' U'\n\nDifferent Commands:");
        eb.addField("WCA", "sc2 - 2x2\nsc3 - 3x3\nsc4 - 4x4\nsc5 - 5x5\nsc6 - 6x6\nsc7 - 7x7", true);
        eb.addField("Subsets", "cross - Cross\nf2l - F2L\noll - OLL\nf2b - F2B\ncmll - CMLL", true);

        event.reply(eb.build());
    }
}
