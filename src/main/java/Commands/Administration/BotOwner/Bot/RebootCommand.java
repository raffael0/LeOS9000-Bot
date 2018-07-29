package Commands.Administration.BotOwner.Bot;

import Util.BotUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class RebootCommand extends Command {
    public RebootCommand(){
        this.name="reboot";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("rebooting...");
        BotUtil.reboot();
    }
}
