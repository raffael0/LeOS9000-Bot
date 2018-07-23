
package Core;

import Commands.Administration.*;
import Commands.Cubing.ScrambleCMD;
import Commands.Fun.ImageCMD;
import Commands.Fun.WebCMD;
import Commands.Information.GithubCMD;
import Commands.Information.InviteCMD;
import Commands.UserInteraction.FeedbackCommand;
import Commands.UserInteraction.GlobalChatCMD;
import Listeners.GlobalChatListener;
import Listeners.ReadyListener;
import Util.BotUtil;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;

import javax.security.auth.login.LoginException;
import java.awt.*;

public class Main {
    static String token = BotUtil.getToken();
    static JDA jda = null;
    static EventWaiter waiter = null;

    public static void main(String[] args){
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);

        CommandClientBuilder cbuilder = new CommandClientBuilder().setPrefix(BotUtil.getPrefix()).setOwnerId(BotUtil.getOwner());

        cbuilder.addCommands(new GameCommand(), new PrefixCommand(), new RebootCommand(), new UpdateCommand(), new GlobalChatCMD(), new FeedbackCommand(), new GithubCMD(), new InviteCMD(),
                new WatchingCommand(), new WebCMD(), new ImageCMD(), new ScrambleCMD());

        CommandClient client = cbuilder.build();

        waiter = new EventWaiter();

        builder.addEventListener(waiter);
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

    public static EventWaiter getWaiter(){ return waiter; };
}