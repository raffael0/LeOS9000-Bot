package commands.administration.botOwner.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Game;

public class GameCommand extends Command {
    public GameCommand(){
        this.name="game";
        this.ownerCommand=true;
        this.arguments="<game>";
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getJDA().getPresence().setGame(Game.playing(event.getArgs()));
        event.reply("now playing " + event.getJDA().getPresence().getGame().getName());
    }
}
