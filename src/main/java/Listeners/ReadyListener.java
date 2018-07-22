package Listeners;

import Util.BotUtil;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event){
        //event.getJDA().getGuildById(BotUtil.getServer()).getTextChannelById(BotUtil.getStatus()).sendMessage("Hello ...ich bin drin !!!").queue();
    }

    @Override
    public void onReconnect(ReconnectedEvent event) {
        event.getJDA().getGuildById(BotUtil.getServer()).getTextChannelById(BotUtil.getStatus()).sendMessage("Hello ...ich wieder da !!!").queue();
    }
}
