package Commands.Cubing;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class ScrambleCMD extends Command {
    public ScrambleCMD(){
        this.name="scramble";
        this.arguments="<scramble>";
        this.help="returns an image of your scramble";
    }

    @Override
    protected void execute(CommandEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("roudai.net")
                    .setPath("/visualcube/visualcube.php")
                    .setParameter("fmt", "png")
                    .setParameter("size", "500")
                    .setParameter("cc","black")
                    .setParameter("bg", "black")
                    .setParameter("alg", event.getArgs().replace('`', '\'').replaceAll("\\s+",""))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet httpget = new HttpGet(uri);
        eb.setImage(httpget.getURI().toString());
        eb.setTitle(event.getArgs());
        event.reply(eb.build());

        uri = null;
        eb.clear();

        try {
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("roudai.net")
                    .setPath("/visualcube/visualcube.php")
                    .setParameter("fmt", "png")
                    .setParameter("size", "500")
                    .setParameter("cc","black")
                    .setParameter("bg", "black")
                    .setParameter("sch","wgoybr")
                    .setParameter("alg", event.getArgs().replace('`', '\'').replaceAll("\\s+",""))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        httpget = new HttpGet(uri);
        eb.setImage(httpget.getURI().toString());
        eb.setFooter("images generated using VisualCube", "http://cube.crider.co.uk/visualcube_4.gif");
        event.reply(eb.build());
    }
}
