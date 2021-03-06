package listeners;

import util.BotUtil;
import util.CubingCMDUtil;
import util.GlobalChatUtil;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class NewServerListener extends ListenerAdapter {
    @Override
    public void onGuildJoin(GuildJoinEvent event){
        CubingCMDUtil.setFalse(event.getGuild().getId());
        event.getJDA().getTextChannelById(BotUtil.getStatus()).sendMessage("just joined server `" + event.getGuild().getName() + "` (" + event.getGuild().getId() + "), which has " + event.getGuild().getMembers().size() + " members").queue();
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event){
        CubingCMDUtil.setFalse(event.getGuild().getId());
        event.getJDA().getTextChannelById(BotUtil.getStatus()).sendMessage("just left server `" + event.getGuild().getName() + "` (" + event.getGuild().getId() + "), which has " + event.getGuild().getMembers().size() + " members").queue();
        if(GlobalChatUtil.isConnected(event.getGuild().getId())){
            GlobalChatUtil.removeServer(event.getGuild().getId(), false);
        }
    }
}
