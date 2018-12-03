package remaster;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FeedPopUp extends JPopupMenu {

    JMenuItem menu1,menu2,menu3;
    public FeedPopUp(FeedPopupListener listener){
        menu1 = new JMenuItem("Nezobrazovat tento článek");
        menu2 = new JMenuItem("Nezobrazovat články z tohoto zdroje");
        menu3 = new JMenuItem("Smazat tento zdroj");
        menu1.addActionListener(a ->{
            listener.hideFeed();
        });
        menu2.addActionListener(a ->{
            listener.hideFeedSource();
        });
        menu3.addActionListener(a ->{
            listener.deleteFeedSource();
        });
        add(menu1);
        add(menu2);
        add(menu3);
    }
}

