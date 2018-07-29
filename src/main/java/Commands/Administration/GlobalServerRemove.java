package Commands.Administration;

import Util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import jdk.nashorn.internal.objects.Global;

public class GlobalServerRemove extends Command {
    public GlobalServerRemove(){
        this.name="gsrm";
        this.ownerCommand=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if(GlobalChatUtil.isConnected(event.getArgs())) {
            GlobalChatUtil.removeServer(event.getArgs(), true);
            event.getJDA().getGuildById(event.getArgs()).getTextChannelsByName("global", true).get(0).sendMessage("You were just kicked from the global chat!").queue();
        } else
            event.reply("Error, that server isn't connected!");
    }
}
