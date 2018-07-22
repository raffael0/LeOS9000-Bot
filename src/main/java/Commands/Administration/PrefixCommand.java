package Commands.Administration;

import Util.BotUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.IOException;

public class PrefixCommand extends Command {
    public PrefixCommand(){
        this.name="prefix";
        this.arguments="<prefix>";
        this.help="changes the prefix";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getArgs().equals("")) {
            event.replyError("please give a character to set the prefix to");
        } else if (event.getArgs().length() > 1) {
            event.replyError("the prefix can only be 1 character long");
        } else {
            BotUtil.setPrefix(event.getArgs());
            event.reply("successfully changed prefix");
            BotUtil.reboot();
        }
    }

}
