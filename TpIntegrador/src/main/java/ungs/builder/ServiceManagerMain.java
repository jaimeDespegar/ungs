package ungs.builder;

import com.google.common.collect.Lists;
import ungs.model.Configuration;
import ungs.services.InformationService;
import ungs.utils.ConfigUtils;
import java.util.List;

public class ServiceManagerMain {


    public InformationService buildService(String pathFile) {
        Configuration configuration = new Configuration(pathFile);

        InformationBuilder builder = new InformationBuilder().withConfiguration(configuration);

        List<String> themes = Lists.newArrayList(configuration.get(ConfigUtils.THEMES).split(","));
        List<String> services = Lists.newArrayList(configuration.get(ConfigUtils.SERVICE).split(","));

        services.forEach(service -> builder.withService(service, configuration.get(ConfigUtils.PACKAGE+service)));
        themes.forEach(theme -> builder.withTheme(theme));

        builder.setLenguajeValue(configuration.get(ConfigUtils.LENGUAJE));
        return builder.build();
    }


    public static void main(String[] args) {
        ServiceManagerMain main = new ServiceManagerMain();
        InformationService service = main.buildService(ConfigUtils.CONFIG_FILE);
        service.getInformation().forEach(i->System.out.println(i.getOrigin()));
    }

}