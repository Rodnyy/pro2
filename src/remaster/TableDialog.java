package remaster;

import model.FeedTableModel;
import model.FeedItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TableDialog extends JDialog {

    FeedTableModel model;

    public TableDialog(List<FeedItem> feedItems){
        setModal(true);
        setLayout(new BorderLayout());

        JPanel toolbar = new JPanel();


        JButton finishBtn = new JButton("Dokončit");
        JButton addButton = new JButton("Přidej");
        JTextField urlField = new JTextField();
        urlField.setPreferredSize(new Dimension(300,30));
        toolbar.add(urlField, BorderLayout.EAST);
        toolbar.add(addButton, BorderLayout.WEST);
        toolbar.add(finishBtn,BorderLayout.WEST);
        add(toolbar,BorderLayout.NORTH);



        model = new FeedTableModel();
        model.setItems(feedItems);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        addButton.addActionListener(action ->{
            FeedItem item = new FeedItem();
            item.setUrl(urlField.getText());
            item.setShouldShow(true);
            item.setAlias("To do");
            item.setAddedMillis(System.currentTimeMillis());
            model.add(item);

            urlField.setText("");
        });

        finishBtn.addActionListener(action -> {
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(null);
    }
    public void open(){
        //
        setVisible(true);
    }
}
