package Commands.Fun;

import Core.Main;
import Util.CubingCMDUtil;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.menu.Paginator;
import com.jagrosh.jdautilities.menu.Slideshow;
import net.dv8tion.jda.core.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ImageCMD extends Command {
    public ImageCMD(){
        this.name="im";
        this.aliases= new String[]{"image"};
        this.help="gets all images from a website";
        this.arguments="<url>";
    }

    @Override
    protected void execute(CommandEvent event) {
        if(CubingCMDUtil.getStatus(event.getGuild().getId()).equals("off")) {
            Slideshow.Builder builder = new Slideshow.Builder();
            builder.setEventWaiter(Main.getWaiter());
            builder.waitOnSinglePage(true);
            builder.wrapPageEnds(true);
            builder.setBulkSkipNumber(3);
            builder.setText("Images from " + event.getArgs());

            Document doc = null;

            try {
                doc = Jsoup.connect(event.getArgs()).ignoreContentType(true).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements images = doc.select("img");

            for (Element e : images) {
                builder.addItems(e.attr("abs:src"));
            }

            Slideshow list = builder.build();
            list.paginate(event.getChannel(), 1);
        }
    }
}
