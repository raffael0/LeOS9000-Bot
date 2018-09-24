package commands.administration.botOwner.globalChat;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import util.GlobalChatUtil;

import java.awt.*;

public class TellServer extends Command {
    public TellServer(){
        this.name="gct";
        this.hidden=true;
        this.ownerCommand=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Message from the developer");
        eb.setColor(Color.RED);
        eb.setDescription(event.getArgs());
        GlobalChatUtil.sendToAllServers(eb.build(), "");
    }
}
