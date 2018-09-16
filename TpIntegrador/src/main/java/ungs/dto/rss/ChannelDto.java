package ungs.dto.rss;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChannelDto {

    @SerializedName("item")
    @Expose
    private List<RssItemDto> listItems = null;

    public List<RssItemDto> getListItems() {
        return listItems;
    }

    public void setListItems(List<RssItemDto> listItems) {
        this.listItems = listItems;
    }

}