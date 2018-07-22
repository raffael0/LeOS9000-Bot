package Commands.Information;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class InviteCMD extends Command {
    public InviteCMD(){
        this.name="invite";
        this.help="sends a link that you can use to invite the bot to your server";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("https://discordapp.com/api/oauth2/authorize?client_id=460120329264693258&permissions=121856&scope=bot\n");
    }
}
