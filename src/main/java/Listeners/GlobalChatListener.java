package Listeners;

import Util.BotUtil;
import Util.GlobalChatUtil;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GlobalChatListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && GlobalChatUtil.isConnected(event.getGuild().getId()) && event.getChannel().getName().toLowerCase().equals("global")
                && !event.getMessage().getContentStripped().equals(BotUtil.getPrefix() + "global disconnect") && !event.getMessage().getContentStripped().equals(BotUtil.getPrefix() + "global")
                && !event.getMessage().getContentStripped().startsWith(BotUtil.getPrefix() + "vk")) {
            String[] ids = GlobalChatUtil.getIdArray();

            for (int i = 0; i < ids.length; i++) {
                if (!event.getMessage().getAttachments().isEmpty() && !ids[i].equals(event.getGuild().getId())) {
                    String content;

                    if(!event.getMessage().getContentStripped().isEmpty()) {
                         content = "```" + event.getMessage().getContentStripped() + "```\n";
                    } else{
                        content = "";
                    }
                        if (!ids[i].equals(event.getGuild().getId())) {
                            event.getJDA().getGuildById(ids[i]).getTextChannelsByName("global", true).get(0).sendMessage(
                                    event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " in \"" +
                                            GlobalChatUtil.getServerName(event.getGuild().getId()) + "\" (" + event.getGuild().getId() + "): " + content + event.getMessage().getAttachments().get(0).getUrl()
                            ).queue();
                        }
                } else if (!ids[i].equals(event.getGuild().getId())) {
                    event.getJDA().getGuildById(ids[i]).getTextChannelsByName("global", true).get(0).sendMessage(
                            event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " in \"" +
                                    GlobalChatUtil.getServerName(event.getGuild().getId()) + "\" (" + event.getGuild().getId() + "): \n" + "```"
                                    + event.getMessage().getContentStripped() + "```"
                    ).queue();
                }
            }

            if (!event.getMessage().getAttachments().isEmpty()) {
                String content;

                if(!event.getMessage().getContentStripped().isEmpty()) {
                    content = "```" + event.getMessage().getContentStripped() + "```\n";
                } else{
                    content = "";
                }

                if (!event.getMessage().getAttachments().isEmpty()) {
                    event.getJDA().getGuildById(BotUtil.getServer()).getTextChannelsByName("global-log", true).get(0).sendMessage(
                            event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " in \"" +
                                    GlobalChatUtil.getServerName(event.getGuild().getId()) + "\" (" + event.getGuild().getId() + "): " + content + event.getMessage().getAttachments().get(0).getUrl()
                    ).queue();
                } else {
                    event.getJDA().getGuildById(BotUtil.getServer()).getTextChannelsByName("global-log", true).get(0).sendMessage(
                            event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator() + " in \"" +
                                    GlobalChatUtil.getServerName(event.getGuild().getId()) + "\" (" + event.getGuild().getId() + "): \n" + "```"
                                    + event.getMessage().getContentStripped() + "```"
                    ).queue();
                }
            }
        }
    }
}
