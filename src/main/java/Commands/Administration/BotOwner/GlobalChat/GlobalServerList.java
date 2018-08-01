package Commands.Administration.BotOwner.GlobalChat;

import Util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class GlobalServerList extends Command {
    public GlobalServerList(){
        this.name="gsl";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Connected Servers");

        String content = "";
        String[] ids = GlobalChatUtil.getIdArray();

        for(int i = 0; i<ids.length; i++){
            content += ids[i] + "   -   " + event.getJDA().getGuildById(ids[i]).getName() + "\n";
        }

        eb.setDescription(content);
        event.reply(eb.build());
    }
}
