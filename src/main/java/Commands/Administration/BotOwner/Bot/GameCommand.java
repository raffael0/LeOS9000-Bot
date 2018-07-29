package Commands.Administration.BotOwner.Bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;

public class GameCommand extends Command {
    public GameCommand(){
        this.name="game";
        this.ownerCommand=true;
        this.arguments="<game>";
        this.cooldown=300;
        this.hidden=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getJDA().getPresence().setGame(Game.playing(event.getArgs()));
        event.reply("now playing " + event.getJDA().getPresence().getGame().getName());
    }
}
