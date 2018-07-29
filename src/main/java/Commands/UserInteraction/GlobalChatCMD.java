package Commands.UserInteraction;

import Util.BotUtil;
import Util.CubingCMDUtil;
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
        if(CubingCMDUtil.getStatus(event.getGuild().getId()).equals("off") && event.getChannel().getName().equals("global")) {
            if (event.getGuild().getTextChannelsByName("global", true).isEmpty()) {
                event.reply("Error, your server needs a text channel named \"global\" to connect to the global chat");
            } else {
                if (event.getArgs().equals("disconnect")) {
                    GlobalChatUtil.removeServer(event.getGuild().getId(), false);
                    event.reply("disconnected from global chat");
                } else if (event.getArgs().equals("connect")) {
                    if (GlobalChatUtil.isConnected(event.getGuild().getId())) {
                        event.reply("Error, you are already connected to the global chat!");
                    } else {
                        GlobalChatUtil.addServer(event.getGuild().getId(), event.getGuild().getName());
                        event.reply("connected to global chat. I do not take any responsibility for the content posted here");

                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setColor(2);

                        String[] arr = GlobalChatUtil.getIdArray();
                        String description = "";

                        for (int i = 0; i < arr.length; i++) {
                            description += GlobalChatUtil.getServerName(arr[i]) + "\n";
                        }

                        eb.setTitle("Connected Servers");
                        eb.setDescription(description);

                        event.reply(eb.build());
                    }

                } else if (event.getArgs().isEmpty()) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setColor(2);

                    String[] arr = GlobalChatUtil.getIdArray();
                    String description = "";

                    for (int i = 0; i < arr.length; i++) {
                        description += GlobalChatUtil.getServerName(arr[i]) + "\n";
                    }

                    eb.setTitle("Connected Servers");
                    eb.setDescription(description);

                    event.reply(eb.build());
                } else {
                    event.reply("Error, invalid arguments given");
                }
            }
        } if(!event.getChannel().getName().equals("global")){
            event.reply("Error, you need to use this command in the #global channel!");
        }
    }
}
