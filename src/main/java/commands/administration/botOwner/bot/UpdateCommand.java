package commands.administration.botOwner.bot;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
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
        try {
            MessageBuilder mb = new MessageBuilder();
            mb.appendCodeBlock("[ATTENTION]\nthe bot will start restarting itself shortly. you can continue talking when it is back online", "asciidoc");
            GlobalChatUtil.sendToAllServers(mb.build(), "");
            event.reply("updating... ");
            Thread.sleep(5000);
            BotUtil.update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
