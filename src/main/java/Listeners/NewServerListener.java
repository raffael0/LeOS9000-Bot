package Listeners;

import Util.CubingCMDUtil;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class NewServerListener extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event){
        CubingCMDUtil.setFalse(event.getGuild().getId());
    }
}
