package Commands.Administration;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Game;

public class WatchingCommand extends Command {
    public WatchingCommand(){
        this.name="watching";
        this.ownerCommand=true;
        this.arguments="<game>";
        this.cooldown=300;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getJDA().getPresence().setGame(Game.watching(event.getArgs()));
        event.reply("now watching " + event.getJDA().getPresence().getGame().getName());
    }
}
