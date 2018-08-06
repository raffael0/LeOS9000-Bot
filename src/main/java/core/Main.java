
package core;

import commands.administration.botOwner.bot.*;
import commands.administration.botOwner.globalChat.*;
import commands.administration.botOwner.InitCubingModeCMD;
import commands.administration.botOwner.ReplyToFeedbackCMD;
import commands.cubing.interactiveCube.CubeCMD;
import commands.cubing.interactiveCube.sizes.*;
import commands.cubing.interactiveCube.MoveCMD;
import commands.cubing.interactiveCube.SelectCMD;
import commands.cubing.wca.*;
import commands.administration.serverOwner.CubingCMD;
import commands.cubing.ScrambleCMD;
import commands.cubing.subsets.cfop.CrossCMD;
import commands.cubing.subsets.cfop.F2LCMD;
import commands.cubing.subsets.cfop.OLLCMD;
import commands.cubing.subsets.roux.CMLLCMD;
import commands.cubing.subsets.roux.F2BCMD;
import commands.fun.ImageCMD;
import commands.fun.WebCMD;
import commands.information.GithubCMD;
import commands.information.InviteCMD;
import commands.userInteraction.FeedbackCommand;
import commands.userInteraction.globalChat.GlobalChatCMD;
import commands.userInteraction.globalChat.GlobalChatKick;
import listeners.GlobalChatListener;
import listeners.NewServerListener;
import util.BotUtil;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import util.GlobalChatUtil;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static String token = BotUtil.getToken();
    static JDA jda = null;
    static EventWaiter waiter = null;
    static CommandClientBuilder cbuilder = null;

    public static void main(String[] args){
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);
        cbuilder = new CommandClientBuilder().setPrefix(BotUtil.getPrefix()).setOwnerId(BotUtil.getOwner());

        cbuilder.addCommands(new ScrambleCMD(), new Scramble2CMD(), new Scramble3CMD(), new Scramble4CMD(), new Scramble5CMD(), new Scramble6CMD(), new Scramble7CMD(), new CMLLCMD(), new F2BCMD(), new CrossCMD(), new F2LCMD(), new OLLCMD(),
                new MoveCMD(), new SelectCMD(), new CubeCMD(), new InteractiveCube2(), new InteractiveCube3(),/* new InteractiveCube4(), new InteractiveCube5(), new InteractiveCube6(), new InteractiveCube7(),*/new GlobalChatCMD(), new GlobalServerRemove(),
                new GlobalChatKick(), new BannedServers(), new GlobalServer(), new UnbanAll(), new DisconnectAll(), new GithubCMD(), new InviteCMD(), new WebCMD(), new ImageCMD(), new FeedbackCommand(), new GameCommand(), new PrefixCommand(),
                new RebootCommand(), new UpdateCommand(), new WatchingCommand(), new CubingCMD(), new ReplyToFeedbackCMD(), new InitCubingModeCMD(),
                new GlobalServerList(), new ShutdownCommand());

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

        GlobalChatUtil.sendToAllServers("The bot is now back online, you can continue talking!", "");
    }

    public static JDA getJDA(){
        return jda;
    }

    public static EventWaiter getWaiter(){ return waiter; };
}