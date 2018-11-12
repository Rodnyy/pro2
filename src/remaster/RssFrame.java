package remaster;

import model.FeedItem;
import rss.RssItem;
import rss.RssParser;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class RssFrame extends JFrame {

    private JPanel content;

    public static void main(String[] args) {
        RssFrame frame = new RssFrame();
        frame.init(800,600);
    }

    private void test() {
        try{
            InputStream is = new FileInputStream(new File("download.xml"));
            List<RssItem> items = new RssParser(is).parseItems();

            for(RssItem item: items){
                content.add(new CardView(item));
            }
            content.doLayout();

        }catch(Exception e){

        }
    }

    private void init(int width, int height) {
        setSize(width,height);
        setTitle("Rss čtečka");
        setLocationRelativeTo(null);//center
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// ukončit program při zavření okna


        JPanel controlPanel = new JPanel(new BorderLayout());
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(action ->{

            List<FeedItem> items = Utils.getAllFeeds();

            new TableDialog(items).open();

            Utils.saveAllFeeds(items);
        });
        controlPanel.add(editButton,BorderLayout.WEST);

        add(controlPanel,BorderLayout.NORTH);

        content = new JPanel(new WrapLayout());
        test();
        add(new JScrollPane(content),BorderLayout.CENTER);

        setVisible(true);
    }

}
