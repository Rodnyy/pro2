package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FeedTableModel extends AbstractTableModel {

    List<FeedItem> feedItems;
    String[] columns = new String[]{"URL", "Check"};

    public FeedTableModel(){
        feedItems = new ArrayList<>();
    }

    public List<FeedItem> getFeedItems(){
        return feedItems;
    }

    public void add(FeedItem feedItem){
        feedItems.add(feedItem);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return feedItems.size();
    }

    @Override
    public int getColumnCount() {
        return  columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FeedItem item = feedItems.get(rowIndex);
        switch (columnIndex){
            case 0: return item.getUrl();
            case 1: return item.isShouldShow();
        }
        return null;
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return String.class;
            case 1: return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        FeedItem item = feedItems.get(rowIndex);
        if (columnIndex == 0){
            item.setUrl((String) aValue);
        }
        if (columnIndex == 1){
            item.setShouldShow((boolean) aValue);
        }
    }
    public void setItems(List<FeedItem> items) {
        this.feedItems = items;
    }
}
