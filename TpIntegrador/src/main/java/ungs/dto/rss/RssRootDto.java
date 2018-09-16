package ungs.dto.rss;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RssRootDto {


    @SerializedName("rss")
    @Expose
    private RssDto rss;

    public RssDto getRss() {
        return rss;
    }

    public void setRss(RssDto rss) {
        this.rss = rss;
    }
}
