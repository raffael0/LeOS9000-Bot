package commands.administration.botOwner.bot;

import util.BotUtil;
import util.GlobalChatUtil;
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
        GlobalChatUtil.sendToAllServers("Attention all users.  The bot will start updating itself shortly. You can continue talking when the bot is back online", "");
        event.reply("updating... ");
        BotUtil.update();
    }
}
