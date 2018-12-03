package remaster;

import rss.RssItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardView extends JPanel {

    private final int ITEM_WIDTH = 180;
    private final int COMPONENT_WIDTH = 160;
    private final int HEIGHT = 1;

    final String startHTML = "<html><p style='width: " + COMPONENT_WIDTH + " px'>";
    final String endHTML = "</p></html>";

    private int descriptionLength = 150;



    public CardView(RssItem item){
        setLayout(new WrapLayout());
        setSize(ITEM_WIDTH,HEIGHT);
        setTitle(item.getTitle());

        if(item.getDescription().length()>descriptionLength){
            setDescription(new String(item.getDescription().substring(0,descriptionLength)+ " ..."));
        }else {
            setDescription(item.getDescription());
        }

        setComponentPopupMenu(new FeedPopUp(new FeedPopupListener() {
            @Override
            public void hideFeed() {

            }

            @Override
            public void hideFeedSource() {

            }

            @Override
            public void deleteFeedSource() {

            }
        }));
        MouseListener click = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(SwingUtilities.isLeftMouseButton(e)){

                    new InfoDialog(item.getDescription());

                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        addMouseListener(click);
        setInfo(item.getLink());

    }

    private void setInfo(String info) {
        JLabel infoLabel = new JLabel();
        infoLabel.setFont(new Font("Courier",Font.ITALIC,10));
        infoLabel.setSize(COMPONENT_WIDTH,HEIGHT);
        infoLabel.setText(String.format("%s%s%s",startHTML,info,endHTML));
        add(infoLabel);
    }

    private void setDescription(String description) {
        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setFont(new Font("Courier",Font.PLAIN,10));
        descriptionLabel.setSize(COMPONENT_WIDTH,HEIGHT);
        descriptionLabel.setText(String.format("%s%s%s",startHTML,description,endHTML));
        add(descriptionLabel);
    }

    private void setTitle(String title){
        JLabel titleLable = new JLabel();
        titleLable.setFont(new Font("Courier",Font.BOLD,12));
        titleLable.setSize(COMPONENT_WIDTH,HEIGHT);
        titleLable.setText(String.format("%s%s%s",startHTML,title,endHTML));
        add(titleLable);
    }
}
