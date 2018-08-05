package Commands.UserInteraction.GlobalChat;

import Util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class GlobalChatKick extends Command {
    public GlobalChatKick(){
        this.name="votekick";
        this.aliases=new String[]{"gkick", "gvote", "vk"};
        this.help="starts a votekick to kick another server from the global chat";
        this.arguments="<server>";
    }

    @Override
    protected void execute(CommandEvent event) {
        GlobalChatUtil u = new GlobalChatUtil();
        String user = event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator();
        String id = (event.getArgs().isEmpty() ? null : event.getArgs());

        if(id == null){
            EmbedBuilder eb = new EmbedBuilder();
            String[] ids = u.getIdArray();
            for(int i = 0; i<ids.length; i++){
                eb.addField(u.getServerName(ids[i]), "id: " + ids[i] + "\nvotes: " + u.getVotes(ids[i]), false);
            }
            event.reply(eb.build());
        } else if(event.getArgs().equals(event.getGuild().getId())){
            event.reply("Error, you can't vote to kick your own server!");
        } else if(id != null && !event.getArgs().equals(event.getGuild().getId())) {
            if (event.getJDA().getGuildById(id).isAvailable()) {
                if (!u.alreadyVoted(id, event.getAuthor().getId())) {
                    u.addVote(id, event.getAuthor().getId());
                     if((u.getVotesNeeded() - u.getVotes(id)) >= 0) {
                        u.sendToAllServers("```" + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " (" + event.getGuild().getId() + ") voted to kick "
                                + event.getJDA().getGuildById(id).getName() + ". " + (u.getVotesNeeded() - u.getVotes(id)) + " more vote(s) are needed to kick the server```", event.getGuild().getId());
                    }
                    event.reply("```you voted to kick " + u.getServerName(id) + " (" + id + "). " + (u.getVotesNeeded() - u.getVotes(id)) + " more vote(s) needed to kick```");
                } else {
                    event.reply("Error, you already voted!");
                }

                if ((u.getVotesNeeded() - u.getVotes(id)) == 0) {
                    u.removeServer(id, true);
                    event.getJDA().getGuildById(id).getTextChannelsByName("global", true).get(0).sendMessage("```you were kicked from the global chat and have to wait 10 minutes before reconnecting again!```").queue();
                }
            } else
                event.reply("Error, that guild does not exist!");
        }
    }
}
