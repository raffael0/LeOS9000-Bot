package commands.administration.botOwner.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.entities.Game;

public class StreamingCMD extends Command {
    public StreamingCMD(){
        this.name="streaming";
        this.hidden=true;
        this.ownerCommand=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if(!Game.isValidStreamingUrl("https://www.twitch.tv/dasleeeo")){
            event.reply("Error, invalid streaming URL");
        } else {
            event.getJDA().getPresence().setGame(Game.streaming(event.getArgs(), "https://www.twitch.tv/dasleeeo"));
            event.reply("now streming " + event.getJDA().getPresence().getGame().getName());
        }
    }
}
