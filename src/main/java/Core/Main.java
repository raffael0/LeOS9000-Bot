
package Core;

import Commands.Administration.*;
import Commands.Cubing.ScrambleCMD;
import Commands.Cubing.Subsets.CFOP.CrossCMD;
import Commands.Cubing.Subsets.CFOP.F2LCMD;
import Commands.Cubing.Subsets.CFOP.OLLCMD;
import Commands.Cubing.Subsets.Roux.CMLLCMD;
import Commands.Cubing.Subsets.Roux.F2BCMD;
import Commands.Cubing.WCA.*;
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

public class Main {
    static String token = BotUtil.getToken();
    static JDA jda = null;
    static EventWaiter waiter = null;

    public static void main(String[] args){
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);

        CommandClientBuilder cbuilder = new CommandClientBuilder().setPrefix(BotUtil.getPrefix()).setOwnerId(BotUtil.getOwner());

        cbuilder.addCommands(new GameCommand(), new PrefixCommand(), new RebootCommand(), new UpdateCommand(), new GlobalChatCMD(), new FeedbackCommand(), new GithubCMD(), new InviteCMD(),
                new WatchingCommand(), new WebCMD(), new ScrambleCMD(), new Scramble2CMD(), new Scramble3CMD(), new Scramble4CMD(), new Scramble5CMD(), new Scramble6CMD(), new Scramble7CMD(),
                new CMLLCMD(), new F2BCMD(), new CrossCMD(), new F2LCMD(), new OLLCMD(), new ImageCMD(), new CubingCMD(), new ReplyToFeedbackCMD());

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

            int servers = 0;
            int users = 0;

            for(int i = 0; i<getJDA().getGuilds().size(); i++){
                servers += 1;
                users += getJDA().getGuilds().get(i).getMembers().size();
            }

            for(int i = 0; i<getJDA().getGuilds().size(); i++){
                System.out.println(getJDA().getGuilds().get(i).getName());
            }

            getJDA().getPresence().setGame(Game.watching(servers + " servers and " + users + " users"));
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