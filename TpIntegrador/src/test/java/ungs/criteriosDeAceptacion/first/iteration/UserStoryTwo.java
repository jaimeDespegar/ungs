package ungs.criteriosDeAceptacion.first.iteration;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ungs.servicesStub.RssConnectorStub;
import ungs.connectors.impl.AbstractConnector;
import ungs.dto.rss.RssItemDto;
import ungs.filters.FilterExecutor;
import ungs.filters.filterFactory.RssFilterFactory;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.services.RssService;
import ungs.transformers.RssTransformer;
import ungs.utils.exceptions.ConfigurationException;
import java.util.List;
import java.util.Map;

public class UserStoryTwo {

    private RssService service;
    private Map<String,String> valuesConfiguration = Maps.newHashMap();
    private String pathFile = "src/test/resources/test-files/services/rss/rss-exists.properties";
    private AbstractConnector connector = new RssConnectorStub();

    @BeforeMethod
    public void init() {
        this.service = new RssService(new RssTransformer(), connector, new RssFilterFactory(connector),new FilterExecutor(), new Configuration(pathFile));
    }

//  Sí se setea el archivo rss-noexists.properties como configuracion en el servicio, la aplicación retorna una ConfigurationException con el mensaje “El archivo de configuración rss-noexists.properties no existe”.
    @Test(expectedExceptions = ConfigurationException.class)
    public void createServiceTwitter_withConfigurationInvalid() {
        this.service.setConfiguration(new Configuration("rss-noexists-properties"));
    }

//  Sí se setea el archivo rss-exists.properties se como configuración en el servicio , se crea correctamente el servicio.
    @Test
    public void createServiceTwitter_withConfigurationValid() {
        this.service.setConfiguration(new Configuration(pathFile));
        Assert.assertNotNull(this.service.getConfiguration());
    }

//  Si el servicio,  con el servidor ok, tiene cargada la url de conexión válida (http://www.urlok.com) el servicio responde true.
    @Test
    public void serviceIsOk() {
        this.service.setValueConfiguration("rss.urlConnection", "http://www.urlok.com");
        Assert.assertTrue(this.service.isServiceOk());
    }

//  Si el servicio,  con el servidor ok, tiene cargada la url de conexión inválida (http://www.urlError.com) el servicio responde false.
    @Test
    public void serviceIsNotOk() {
        this.service.setValueConfiguration("rss.urlConnection", "http://www.urlError.com");
        Assert.assertFalse(this.service.isServiceOk());
    }

//  Sí hago una consulta general al servicio me devuelve una lista con 2 elementos de id 3 y 4.
    @Test
    public void getData_isOk() {
        List<RssItemDto> list = this.service.getData();
        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void getDataInformation_isOk() {
        List<InformationDto> list = this.service.getInformation(service.getData());
        Assert.assertEquals(list.size(), 2);
    }
//  Sí inició el servicio con la url1 y url2 , el servicio lanza una ConfigurationException con el mensaje “La url no es válida”.
    @Test(expectedExceptions = ConfigurationException.class)
    public void serviceIsNotOk_whenUrlsAreInvalid() {
        this.valuesConfiguration.put("rss.theme.sport", "http://www.urlOk.com");
        this.valuesConfiguration.put("rss.theme.politics", "http://www.urlError.com");
        this.service = new RssService(new RssTransformer(), connector, new RssFilterFactory(connector), new FilterExecutor(), new Configuration(valuesConfiguration));
    }

//  Sí inició el servicio con la url1 , el servicio se crea correctamente.
    @Test
    public void createService_whenUrlLoadIsOk_thenIsCreationOk() {
        this.valuesConfiguration.put("rss.theme.sport", "http://www.urlOk.com");
        this.service = new RssService(new RssTransformer(), connector, new RssFilterFactory(connector), new FilterExecutor(), new Configuration(valuesConfiguration));
    }

//  Si el valor de la configuración de cantidad de respuesta es 1 devuelvo 1 elemento.
    @Test
    public void getData_whenSizeReturnIsOne_thenReturnOneElement() {
        this.service.setValueConfiguration("rss.response.count", "1");
        List<RssItemDto> list = service.getData();
        Assert.assertEquals(list.size(), 1);
    }

    //    Si el valor de la configuración de cantidad de respuesta es 2 devuelvo 2 elementos.
    @Test
    public void getData_whenSizeReturnIsTwo_thenReturnTwoElement() {
        this.service.setValueConfiguration("rss.response.count", "2");
        List<RssItemDto> list = service.getData();
        Assert.assertEquals(list.size(), 2);
    }

    //    Si el valor de la configuración de cantidad de respuesta es 0 devuelvo 2 elemento.
    @Test
    public void getData_whenSizeReturnIsZero_thenReturnTwoElement() {
        this.service.setValueConfiguration("rss.response.count", "0");
        List<RssItemDto> list = service.getData();
        Assert.assertEquals(list.size(), 2);
    }

}