package ungs.services;

import com.google.common.collect.Lists;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.utils.ConfigUtils;
import java.util.List;

public class InformationService {

    private List<Service> services;
    private List<String> themes;
    private List<String> filters;

    private Configuration configuration;

    public InformationService() {
        this.services = Lists.newArrayList();
    }

    private void init() {
        List<String> filesByServices = Lists.newArrayList(ConfigUtils.RSS_FILE, ConfigUtils.TWITTER_FILE);
    }

    public List<String> getThemes() {
        return Lists.newArrayList();
    }

    public List<InformationDto> getInformation() {
        return Lists.newArrayList();
    }

}