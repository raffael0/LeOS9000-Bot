
package Core;

import Commands.Administration.GameCommand;
import Commands.Administration.PrefixCommand;
import Commands.Administration.RebootCommand;
import Commands.Administration.UpdateCommand;
import Commands.UserInteraction.FeedbackCommand;
import Commands.UserInteraction.GlobalChatCMD;
import Listeners.GlobalChatListener;
import Listeners.ReadyListener;
import Util.BotUtil;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;

import javax.security.auth.login.LoginException;

public class Main {
    static String token = BotUtil.getToken();
    static JDA jda = null;

    public static void main(String[] args){
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);

        CommandClientBuilder cbuilder = new CommandClientBuilder().setPrefix(BotUtil.getPrefix()).setOwnerId(BotUtil.getOwner());

        cbuilder.addCommands(new GameCommand(), new PrefixCommand(), new RebootCommand(), new UpdateCommand(), new GlobalChatCMD(), new FeedbackCommand());

        CommandClient client = cbuilder.build();

        builder.addEventListener(new ReadyListener());
        builder.addEventListener(new GlobalChatListener());
        builder.addEventListener(client);

        try {
            jda = builder.buildBlocking();
            int count = 0;
            for (Guild guild : (jda.getGuilds())) {
                count++;
            }
            jda.getPresence().setGame(Game.watching("anime"));
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static JDA getJDA(){
        return jda;
    }
}