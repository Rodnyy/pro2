package remaster;

import model.TableModel;

import javax.swing.*;
import java.awt.*;

public class TableDialog extends JDialog {

    public TableDialog(){
        setModal(true);
        setLayout(new BorderLayout());

        JPanel toolbar = new JPanel();


        JButton finishBtn = new JButton("Dokončit");
        toolbar.add(finishBtn,BorderLayout.EAST);
        add(toolbar,BorderLayout.NORTH);

        TableModel model = new TableModel();
        JTable talbe = new JTable(model);
        add(new JScrollPane(talbe), BorderLayout.CENTER);

        finishBtn.addActionListener(action -> {
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(null);
    }
    public void open(){
        setVisible(true);
    }
}
