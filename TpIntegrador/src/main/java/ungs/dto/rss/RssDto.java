package ungs.dto.rss;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RssDto {

    @SerializedName("channel")
    @Expose
    private ChannelDto channel;

    public ChannelDto getChannel() {
        return channel;
    }

    public void setChannel(ChannelDto channelDto) {
        this.channel = channelDto;
    }
}
