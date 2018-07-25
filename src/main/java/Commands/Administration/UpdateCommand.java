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
        GlobalChatUtil.sendToAllServers("Attention all users.  The bot will start updating itself shortly. All servers connected to the" +
                " global chat with be disconnected. Please be patient and we apologise for the inconvenience. If you see that the bot is online again, just reconnect to the global chat using .global connect", "123456789");
        event.reply("updating... ");
        BotUtil.update();
    }
}
