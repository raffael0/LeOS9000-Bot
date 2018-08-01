package Commands.Fun;

import Util.CubingCMDUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebCMD extends Command {
    public WebCMD(){
        this.name="web";
        this.aliases= new String[]{"website"};
        this.help="gets the content from a website";
        this.arguments="<url>";
    }

    @Override
    protected void execute(CommandEvent event) {
        if(CubingCMDUtil.getStatus(event.getGuild().getId()).equals("off")) {
            Document doc = null;
            try {
                doc = Jsoup.connect(event.getArgs()).ignoreContentType(true).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String reply = "";
            EmbedBuilder eb = new EmbedBuilder();

            Elements text = doc.select("p");
            Elements headlines = doc.select("h1,h2,h3,h4,h5");

            int length = 0;
            int pos = 0;

            for (Element e : text) {
                reply += e.text() + "\n\n";
                length += reply.length();

                if (pos < headlines.toArray().length && length >= 600) {
                    eb.addField("\n" + headlines.get(pos).text() + "\n", reply, true);
                    length = 0;
                    pos++;
                    reply = "";
                } else if (length >= 600) {
                    eb.addField("", reply, true);
                    length = 0;
                    reply = "";
                }
            }

            event.reply(eb.build());
        }
    }
}
