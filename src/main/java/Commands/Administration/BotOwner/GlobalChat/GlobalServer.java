package Commands.Administration.BotOwner.GlobalChat;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class GlobalServer extends Command {
    public GlobalServer(){
        this.name="gs";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.addField("gsbl", "returns a list of all banned servers", false);
        eb.addField("gsda", "disconnects all servers", false);
        eb.addField("gsua", "unbans all servers", false);
        eb.addField("gsl", "returns a list of all connected servers including the id", false);
        eb.addField("gsrm", "kicks a server", false);
        eb.setColor(Color.RED);

        event.reply(eb.build());
    }
}
