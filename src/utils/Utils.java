package utils;

import model.FeedItem;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static void saveFeedItem(FeedItem item){

        try{
            File file = new File("feedItems.csv");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.readLine(); // přeskočit první řádek
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains(item.getUrl())){
                    //Přepiš řádek
                }
            }

        }catch (Exception e){

        }

    }

    public static List<FeedItem> getAllFeeds() {
        List<FeedItem> feedItems = new ArrayList<>();
        try {
            File file = new File("feedItems.csv");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.readLine(); // přeskočit první řádek
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                feedItems.add(FeedItem.parseFromCSV(line));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedItems;
    }

    public static void saveAllFeeds(List<FeedItem> items) {
        try {
            File file = new File("feedItems.csv");
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("url;addedMillis;shouldShow;alias");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            for (FeedItem item : items){
                bufferedWriter.write(item.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getMillisFromDateString (String pubDate){
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

        try{
            Date date =  format.parse(pubDate);
            return date.getTime();

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
