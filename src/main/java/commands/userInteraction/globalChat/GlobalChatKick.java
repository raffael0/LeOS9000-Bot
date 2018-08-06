package commands.userInteraction.globalChat;

import util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class GlobalChatKick extends Command {
    public GlobalChatKick(){
        this.name="votekick";
        this.aliases=new String[]{"gkick", "gvote", "vk"};
        this.help="starts a votekick to kick another server from the global chat";
        this.arguments="<server>";
        this.category=new Category("Global Chat");
    }

    @Override
    protected void execute(CommandEvent event) {
        GlobalChatUtil u = new GlobalChatUtil();
        String user = event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator();
        String[] ids = u.getIdArray();
        int choice = (event.getArgs().isEmpty() ? -1 : Integer.parseInt(event.getArgs()));

        if(choice == -1){
            EmbedBuilder eb = new EmbedBuilder();
            for(int i = 0; i<ids.length; i++){
                eb.addField("[" + i + "] " + u.getServerName(ids[i]), "id: " + ids[i] + "\nvotes: " + u.getVotes(ids[i]), false);
            }
            event.reply(eb.build());
        } else if(ids[choice].equals(event.getGuild().getId())){
            event.reply("Error, you can't vote to kick your own server!");
        } else if(choice < ids.length && !ids[choice].equals(event.getGuild().getId())) {
            if (event.getJDA().getGuildById(ids[choice]).isAvailable()) {
                if (!u.alreadyVoted(ids[choice], event.getAuthor().getId())) {
                    u.addVote(ids[choice], event.getAuthor().getId());
                     if((u.getVotesNeeded() - u.getVotes(ids[choice])) >= 0) {
                        u.sendToAllServers("```" + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " (" + event.getAuthor().getId() + ") voted to kick "
                                + event.getJDA().getGuildById(ids[choice]).getName() + ". " + (u.getVotesNeeded() - u.getVotes(ids[choice])) + " more vote(s) are needed to kick the server```", event.getGuild().getId());
                    }
                    event.reply("```you voted to kick " + u.getServerName(ids[choice]) + " (" + ids[choice] + "). " + (u.getVotesNeeded() - u.getVotes(ids[choice])) + " more vote(s) needed to kick```");
                } else {
                    event.reply("Error, you already voted!");
                }

                if ((u.getVotesNeeded() - u.getVotes(ids[choice])) == 0) {
                    u.removeServer(ids[choice], true);
                    event.getJDA().getGuildById(ids[choice]).getTextChannelsByName("global", true).get(0).sendMessage("```you were kicked from the global chat and have to wait 10 minutes before reconnecting again!```").queue();
                }
            } else
                event.reply("Error, that guild does not exist!");
        } else if(choice > ids.length){
            event.reply("Error, invalid choice");
        }
    }
}
