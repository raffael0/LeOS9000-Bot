package Commands.Administration.BotOwner.GlobalChat;

import Util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GlobalServerRemove extends Command {
    public GlobalServerRemove(){
        this.name="gsrm";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if(GlobalChatUtil.isConnected(event.getArgs())) {
            GlobalChatUtil.removeServer(event.getArgs(), true);
            event.getJDA().getGuildById(event.getArgs()).getTextChannelsByName("global", true).get(0).sendMessage("You were kicked from the global chat!").queue();
        } else
            event.reply("Error, that server isn't connected!");
    }
}
