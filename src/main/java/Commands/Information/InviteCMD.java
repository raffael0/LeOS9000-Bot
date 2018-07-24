package Commands.Information;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class InviteCMD extends Command {
    public InviteCMD(){
        this.name="invite";
        this.help="sends a link that you can use to invite the bot to your server";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setTitle("Invite");
        eb.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());
        eb.addField("Support Server", "https://discord.gg/DjRpzGx", true);
        eb.addField("Invite the bot to your server", "https://discordapp.com/api/oauth2/authorize?client_id=460120329264693258&permissions=121856&scope=bot\n", true);

        event.reply(eb.build());
    }
}
