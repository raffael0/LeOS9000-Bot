package commands.administration.botOwner.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Game;

public class ListeningCommand extends Command {
    public ListeningCommand(){
        this.name="listening";
        this.hidden=true;
        this.ownerCommand=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getJDA().getPresence().setGame(Game.listening(event.getArgs()));
        event.reply("now listening to " + event.getJDA().getPresence().getGame().getName());
    }
}
