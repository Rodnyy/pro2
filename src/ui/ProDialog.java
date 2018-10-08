package ui;

import model.ToDoItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProDialog extends JDialog {

    private ToDoItem item;

    public ProDialog (){
        setModal(true);
        

        JTextArea txtContent = new JTextArea("Vložte obsah");
        add(txtContent, BorderLayout.NORTH);

        JButton btnOK = new JButton("OK");
        add(btnOK);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item = new ToDoItem(txtContent.getText());

                setVisible(false);
            }
        });

        pack();
        setLocationRelativeTo(null);

    }

    public ToDoItem getItem(){
        setVisible(true); //zmrazí puvodní vlakno
        return  item;
    }
}
