package commands.administration.serverOwner;

import util.CubingCMDUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;

public class CubingCMD extends Command {
    public CubingCMD(){
        this.name="cubing";
        this.aliases= new String[]{"cubingmode", "cubemode"};
        this.arguments="on/off";
        this.help="toggles the cubing only mode. Off by default";
        this.userPermissions = new Permission[]{Permission.MANAGE_SERVER};
        this.category=new Category("Administration");
    }

    @Override
    protected void execute(CommandEvent event) {
        if(!CubingCMDUtil.exists(event.getGuild().getId())){
            CubingCMDUtil.setFalse(event.getGuild().getId());
        }

        if(event.getArgs().isEmpty()){
            event.reply("cubing only mode is currently turned: " + CubingCMDUtil.getStatus(event.getGuild().getId()));
        } else if(event.getArgs().equals("on")){
            CubingCMDUtil.setTrue(event.getGuild().getId());
            event.reply("Set cubing only mode to: on");
        } else if(event.getArgs().equals("off")){
            CubingCMDUtil.setFalse(event.getGuild().getId());
            event.reply("Set cubing only mode to: off");
        }
    }
}
