package Commands.Administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class ReplyToFeedbackCMD extends Command {
    public ReplyToFeedbackCMD(){
        this.name="reply";
        this.ownerCommand=true;
        this.hidden=true;
        this.arguments="<id | message>";
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] cmd = event.getArgs().split("\\|");
        sendPM(event, cmd[0].trim(), cmd[1].trim());
    }

    private void sendPM(CommandEvent event, String id, String message){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("The bots developer responded to your feedback!");
        eb.setDescription(message);
        eb.setFooter("Please do not reply to this message by sending another feedback message. Please join the support server instead", event.getSelfUser().getAvatarUrl());
        event.getMessage().addReaction("✅").queue();
        event.getJDA().getUserById(id).openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(eb.build()).queue());
    }
}
