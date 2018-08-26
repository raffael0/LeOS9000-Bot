package commands.administration.botOwner.bot;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BashCMD extends Command {
    public BashCMD(){
        this.name="bash";
        this.hidden=true;
        this.ownerCommand=true;
    }

    @Override
    protected void execute(CommandEvent event) {
        String result = "";
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(event.getArgs());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null && in.readLine()!=null) {
                result += inputLine + "\n";
            }
            in.close();
            if(!result.equals("")) {
                event.reply("```" + result + "```");
            } else {
                event.reply("empty response");
            }
        } catch (IOException e) {
            System.out.println(e);
            event.reply("Error, command could not be executed");
        }
    }
}
