package Listeners;

import Util.BotUtil;
import Util.CubingCMDUtil;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class NewServerListener extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event){
        CubingCMDUtil.setFalse(event.getGuild().getId());

        event.getJDA().getTextChannelById(BotUtil.getStatus()).sendMessage("Just joined server \"" + event.getGuild().getName() + "\" (" + event.getGuild().getId() + "), which has " + event.getGuild().getMembers().size() + " members").queue();
    }
}
