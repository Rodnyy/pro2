package ui;

import model.TableModel;
import model.ToDoItem;
import rss.RssItem;
import rss.RssParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProFrame extends JFrame {

    static int width = 600;
    static int height = 600;
    private TableModel model;

    public static void main(String... args) {

        ProFrame proFrame = new ProFrame();
        proFrame.init(width, height);

    }

    private void init(int width, int height) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
        setTitle("Programování 2");

        JPanel toolbar = new JPanel();
        add(toolbar, BorderLayout.NORTH);

        JButton button = new JButton();
        button.setText("Přidat poznámku");
        toolbar.add(button);

        button.addActionListener(e -> {
            ToDoItem item = new ProDialog().getItem();
            model.add(item);
        });

        JButton buttSave = new JButton();
        buttSave.setText("Ulož");
        toolbar.add(buttSave);

        buttSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveItems();
            }
        });

        JButton buttLoad = new JButton();
        buttLoad.setText("Načti");
        toolbar.add(buttLoad);

        buttLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadItems();
            }
        });

        model = new TableModel();

        //JTable table = new JTable(model);
        //add(new JScrollPane(table), BorderLayout.CENTER);

        JTextField txtFieldRssUrl = new JTextField("Vaše URL adresa");
        add(txtFieldRssUrl,BorderLayout.CENTER);


        JButton buttParse = new JButton();
        buttParse.setText("Načti URL");
        buttParse.addActionListener(e -> {
            addFeed(txtFieldRssUrl.getText());
        });
        toolbar.add(buttParse);

        pack();

        setLocationRelativeTo(null);

        reedFeed();
    }

    private void parse(String url) {
        try {

           // RssParser parser = new RssParser(new FileInputStream(new File("download.xml")));

           // String url = "http://www.eurofotbal.cz/feed/rss/premier-league/";
            URLConnection connection= new URL(url).openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            RssParser parser = new RssParser(stream);

            List<RssItem> rssItems = parser.parseItems();
            for (RssItem rssItem : rssItems) {
                System.out.println(rssItem.toString());
            }
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveItems() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("our.db")));
            stream.writeObject(model.getItems());
            stream.flush();
            stream.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void loadItems() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("our.db")));
            List<ToDoItem> items = (List<ToDoItem>) stream.readObject();
            stream.close();
            model.setItems(items);
            model.fireTableDataChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void  addFeed(String url){
        try{
        File file = new File("feed.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write(url);
        writer.newLine();
        writer.flush();

        }catch (Exception e){

        }
    }

    private void reedFeed (){
        try{
            List<String> urls = new ArrayList<>();
            File file = new File("feed.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            while ((line = reader.readLine())!=null){
                urls.add(line);
            }
            for(String url: urls){
                System.out.println(url);
            }

        }catch (Exception e){


        }

    }
}

