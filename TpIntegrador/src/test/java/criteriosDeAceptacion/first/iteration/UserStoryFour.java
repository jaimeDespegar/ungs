package criteriosDeAceptacion.first.iteration;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ungs.builder.InformationBuilder;
import ungs.services.InformationService;
import ungs.services.RssService;
import ungs.services.TwitterService;
import ungs.utils.exceptions.ConfigurationException;

public class UserStoryFour {

    private InformationBuilder builder;

    @BeforeClass
    public void init() {
        this.builder = new InformationBuilder();
    }

//    Sí se crea el servicio general con la data1, la lista de servicios tiene tamaño 1 y es un RssService.
    @Test
    public void createService_withServiceRss_isOk() {
        InformationService service = new InformationBuilder().withService("rss", "ungs.services.RssService").build();
        Assert.assertEquals(service.getServices().size(), 1);
        Assert.assertEquals(service.getServices().get(0).getClass(), RssService.class);
    }

//    Sí se crea el servicio general con la data2, se lanza una ConfiguraciónExcepción con el mensaje “Servicio twitterSinpackage inválido, no se pudo crear”.
    @Test(expectedExceptions = ConfigurationException.class)
    public void createService_withServiceRss_isNotOk() {
        InformationService service = this.builder.withService("twitter", "").build();
    }

//    Sí se crea el servicio general con la data3, se lanza una ConfiguraciónExcepción con el mensaje “Servicio noExisto inválido, no se pudo crear”.
    @Test(expectedExceptions = ConfigurationException.class)
    public void createService_withServiceNotExists_isNotOk() {
        InformationService service = this.builder.withService("noExiste", "noExiste").build();
    }

//    Sí se crea el servicio general con la data4, la lista de servicios tiene tamaño 2 y es un RssService y TwitterService.
    @Test
    public void createService_withServiceRssAndTwitter_isOk() {
        InformationService service = this.builder.withService("rss", "ungs.services.RssService")
                                                 .withService("twitter", "ungs.services.TwitterService")
                                                 .build();
        Assert.assertEquals(service.getServices().size(), 2);
        Assert.assertEquals(service.getServices().get(0).getClass(), RssService.class);
        Assert.assertEquals(service.getServices().get(1).getClass(), TwitterService.class);
    }

}
