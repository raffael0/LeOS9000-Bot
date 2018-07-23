package Commands.Fun;

import Core.Main;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ImageCMD extends Command {
    public ImageCMD(){
        this.name = "image";
        this.aliases = new String[]{"im"};
        this.help="gets images from a web page";
        this.arguments="<url>";
    }

    @Override
    protected void execute(final CommandEvent event) {
        Document doc = null;
        try {
            doc = Jsoup.connect(event.getArgs()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        event.reply("There are " + images.toArray().length + " images on this page. Please enter a number");
    }
}
