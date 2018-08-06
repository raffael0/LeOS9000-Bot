package commands.information;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GithubCMD extends Command {
    public GithubCMD(){
        this.name="github";
        this.help="replies with a link to my github repo";
        this.category=new Category("Information");
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("https://github.com/nichtdasleo/LeOS9000-bot");
    }
}
