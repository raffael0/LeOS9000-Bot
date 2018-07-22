package Commands.UserInteraction;

import Util.GlobalChatUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

public class GlobalChatCMD extends Command {
    public GlobalChatCMD(){
        this.name="global";
        this.arguments="<connect/disconnect>";
        this.help="connects to the global chat";
    }

    @Override
    protected void execute(CommandEvent event) {
        if(event.getGuild().getTextChannelsByName("global", true).isEmpty()){
            event.reply("Error, your server needs a text channel named \"global\" to connect to the global chat");
        } else{
            if(event.getArgs().equals("disconnect")){
                GlobalChatUtil.removeServer(event.getGuild().getId());
                event.reply("disconnected from global chat");
            } else if(event.getArgs().equals("connect")){
                GlobalChatUtil.addServer(event.getGuild().getId(), event.getGuild().getName());
                event.reply("connected to global chat. I do not take any responsibility for the content posted here");

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(2);

                String[] arr = GlobalChatUtil.getIdArray();
                String description = "";

                for(int i = 0; i<arr.length; i++){
                    description += GlobalChatUtil.getServerName(arr[i]) + "\n";
                }

                eb.setTitle("Connected Servers");
                eb.setDescription(description);

                event.reply(eb.build());

            } else if(event.getArgs().isEmpty()){
                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(2);

                String[] arr = GlobalChatUtil.getIdArray();
                String description = "";

                for(int i = 0; i<arr.length; i++){
                    description += GlobalChatUtil.getServerName(arr[i]) + "\n";
                }

                eb.setTitle("Connected Servers");
                eb.setDescription(description);

                event.reply(eb.build());
            }else {
                event.reply("Error, invalid arguments given");
            }
        }
    }
}
