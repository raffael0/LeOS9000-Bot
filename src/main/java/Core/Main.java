
package Core;

import Commands.Administration.BotOwner.Bot.*;
import Commands.Administration.BotOwner.GlobalChat.BannedServers;
import Commands.Administration.BotOwner.GlobalChat.GlobalServerList;
import Commands.Administration.BotOwner.GlobalChat.GlobalServerRemove;
import Commands.Administration.BotOwner.InitCubingModeCMD;
import Commands.Administration.BotOwner.ReplyToFeedbackCMD;
import Commands.Cubing.WCA.*;
import Commands.Administration.ServerOwner.CubingCMD;
import Commands.Cubing.ScrambleCMD;
import Commands.Cubing.Subsets.CFOP.CrossCMD;
import Commands.Cubing.Subsets.CFOP.F2LCMD;
import Commands.Cubing.Subsets.CFOP.OLLCMD;
import Commands.Cubing.Subsets.Roux.CMLLCMD;
import Commands.Cubing.Subsets.Roux.F2BCMD;
import Commands.Fun.ImageCMD;
import Commands.Fun.WebCMD;
import Commands.Information.GithubCMD;
import Commands.Information.InviteCMD;
import Commands.UserInteraction.FeedbackCommand;
import Commands.UserInteraction.GlobalChat.GlobalChatCMD;
import Commands.UserInteraction.GlobalChat.GlobalChatKick;
import Listeners.GlobalChatListener;
import Listeners.NewServerListener;
import Util.BotUtil;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String token = BotUtil.getToken();
    static JDA jda = null;
    static EventWaiter waiter = null;

    public static void main(String[] args){
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);

        CommandClientBuilder cbuilder = new CommandClientBuilder().setPrefix(BotUtil.getPrefix()).setOwnerId(BotUtil.getOwner());

        cbuilder.addCommands(new GameCommand(), new PrefixCommand(), new RebootCommand(), new UpdateCommand(), new GlobalChatCMD(), new FeedbackCommand(), new GithubCMD(), new InviteCMD(),
                new WatchingCommand(), new WebCMD(), new ScrambleCMD(), new Scramble2CMD(), new Scramble3CMD(), new Scramble4CMD(), new Scramble5CMD(), new Scramble6CMD(), new Scramble7CMD(),
                new CMLLCMD(), new F2BCMD(), new CrossCMD(), new F2LCMD(), new OLLCMD(), new ImageCMD(), new CubingCMD(), new ReplyToFeedbackCMD(), new InitCubingModeCMD(), new GlobalServerList(),
                new GlobalServerRemove(), new GlobalChatKick(), new BannedServers(), new ShutdownCommand());

        CommandClient client = cbuilder.build();

        waiter = new EventWaiter();

        builder.addEventListener(waiter);
        builder.addEventListener(new NewServerListener());
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
            List<User> members = new ArrayList<>();

            System.out.println("=== Users ===");

            for(int i = 0; i<getJDA().getGuilds().size(); i++){
                for(int j = 0; j<getJDA().getGuilds().get(i).getMembers().size(); j++){
                    if(!members.contains(getJDA().getGuilds().get(i).getMembers().get(j).getUser())){
                        users++;
                        members.add(getJDA().getGuilds().get(i).getMembers().get(j).getUser());
                        System.out.println("User:  " + getJDA().getGuilds().get(i).getMembers().get(j).getUser().getName());
                    }
                }
                servers++;
            }

            System.out.println("=== Servers ===");

            for(int i = 0; i<getJDA().getGuilds().size(); i++){
                System.out.println("Server: " + getJDA().getGuilds().get(i).getName());
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