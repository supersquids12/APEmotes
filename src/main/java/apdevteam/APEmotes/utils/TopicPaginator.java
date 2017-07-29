package apdevteam.APEmotes.utils;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.bukkit.util.ChatPaginator.CLOSED_CHAT_PAGE_HEIGHT;

public class TopicPaginator {
    private String title;
    private List<String> lines = new ArrayList<>();

    public TopicPaginator(String title){
        this.title = title;
    }

    public boolean addLine(String line){
        boolean result = lines.add(line);
        Collections.sort(lines);
        return result;
    }

    /**
     * Page numbers begin at 1
     * @param pageNumber
     * @return An array of lines to send as a page
     */
    public String[] getPage(int pageNumber){
        if(!isInBounds(pageNumber))
            throw new IndexOutOfBoundsException("Page number " + pageNumber + " exceeds bounds <" + 1 + "," + getPageCount() + ">");
        String[] tempLines = new String[lines.size() < pageNumber * (CLOSED_CHAT_PAGE_HEIGHT-1)  ? lines.size()%CLOSED_CHAT_PAGE_HEIGHT : CLOSED_CHAT_PAGE_HEIGHT-1];
        tempLines[0] = "§e§l--- §r§6" + title +"§e§l-- §r§6page §c" + pageNumber + "§e/§c" + getPageCount() + " §e§l---";
        for(int i = 1; i< tempLines.length; i++){
            Bukkit.getLogger().info("" + i);
            tempLines[i] = lines.get(((CLOSED_CHAT_PAGE_HEIGHT-1) * (pageNumber-1)) + i);
        }
        return tempLines;
    }

    public int getPageCount(){
        return (int)Math.ceil(((double)lines.size())/(CLOSED_CHAT_PAGE_HEIGHT-1));
    }

    public boolean isInBounds(int pageNumber){
        Bukkit.getLogger().info("total pages: " + getPageCount() + " input: " + pageNumber);
        return pageNumber > 0 && pageNumber <= getPageCount();
    }
}