package Commands.Administration.BotOwner;

import Core.Main;
import Util.CubingCMDUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Guild;

import java.util.List;

public class InitCubingModeCMD extends Command {
    public InitCubingModeCMD(){
        this.name="initcubing";
        this.ownerCommand=true;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        List<Guild> guilds = Main.getJDA().getGuilds();
        int num = 0;
        for(int i = 0; i<guilds.size(); i++){
            if(!CubingCMDUtil.exists(guilds.get(i).getId())){
                CubingCMDUtil.setFalse(guilds.get(i).getId());
                num++;
            }
        }
        event.reply("Fixed the cubing mode on " + num + " servers");
    }
}
