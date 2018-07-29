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
        eb.setTitle("New Feedback Message!");
        eb.addField("Feedback: ", event.getArgs(), false);
        eb.addField("User: ",event.getMessage().getAuthor().getName() + "#" + event.getMessage().getAuthor().getDiscriminator(), false);
        eb.addField("ID: ", event.getAuthor().getId(), false);
        eb.addField("Server: ", event.getGuild().getName() + " (" + event.getGuild().getId() + ")", false);
        eb.addField("Channel: ", event.getChannel().getName(), false);
        eb.setThumbnail(event.getAuthor().getAvatarUrl());
        event.getJDA().getGuildById(BotUtil.getServer()).getTextChannelById(BotUtil.getChannel()).sendMessage(eb.build()).queue();

        event.reply("successfully sent feedback to the bots developer!");
    }
}

