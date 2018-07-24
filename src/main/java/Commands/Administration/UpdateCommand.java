package Commands.Administration;

import Util.BotUtil;
import Util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class UpdateCommand extends Command {
    public UpdateCommand(){
        this.name="update";
        this.help="updates the bot";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        GlobalChatUtil.sendToAllServers("THE BOT WILL SHORTLY START UPDATING ITSELF. ALL SERVERS CONNECTED TO THE GLOBAL CHAT WILL GET DISCONNECTED", "");
        event.reply("updating... ");
        BotUtil.update();
    }
}
