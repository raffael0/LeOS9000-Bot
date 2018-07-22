package Commands.UserInteraction;

import Util.BotUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class FeedbackCommand extends Command {
    public FeedbackCommand(){
        this.name = "feedback";
        this.help = "use this command to give feedback";
        this.arguments = "<feedback>";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("User : " + event.getMessage().getAuthor().getName() + "#" + event.getMessage().getAuthor().getDiscriminator() + " on " + event.getGuild().getName() + " in #" + event.getChannel().getName());
        eb.setDescription(event.getArgs());
        event.getJDA().getGuildById(BotUtil.getServer()).getTextChannelById(BotUtil.getChannel()).sendMessage(eb.build()).queue();
    }
}

