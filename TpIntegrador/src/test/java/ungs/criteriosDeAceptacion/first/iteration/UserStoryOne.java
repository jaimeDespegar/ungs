package ungs.criteriosDeAceptacion.first.iteration;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ungs.builders.services.ServiceBuilder;
import ungs.dto.TwitterObjectDto;
import ungs.model.InformationDto;
import ungs.services.TwitterService;
import ungs.utils.exceptions.ConfigurationException;
import java.util.List;
import java.util.Map;

public class UserStoryOne {

//    Sí se setea el archivo twitter-noexists.properties como configuracion en el servicio, la aplicación retorna una ConfigurationException
//    con el mensaje “El archivo de configuración twitter.properties no existe”.

    private TwitterService twitterServiceStub;
    private Map<String,String> valuesConfiguration = Maps.newHashMap();
    String path = "src/test/resources/test-files/services/twitter/twitter-exists.properties";

    @BeforeMethod
    public void init() {
        this.twitterServiceStub = ServiceBuilder.create().buildTwitter(path).build();
    }



    @Test(expectedExceptions = ConfigurationException.class)
    public void createServiceTwitter_withConfigurationInvalid() {
        this.twitterServiceStub = ServiceBuilder.create().buildTwitter("twitter-noexistis.properties").build();;
    }

//    Sí se setea el archivo twitter-exists.properties se como configuración en el servicio , se crea correctamente el servicio.
    @Test
    public void createServiceTwitter_withConfigurationValid() {
        this.twitterServiceStub = ServiceBuilder.create().buildTwitter(path).build();
        Assert.assertNotNull(twitterServiceStub.getConfiguration());
    }


//    Sí colocó una configuración inválida de la cuenta  (apiKey incorrecta) , la consulta sí está habilitado el servicio devuelve false.
    @Test
    public void accountTwitterInvalid_thenIsServiceNotOk() {
        this.setValuesAccountUser("invalid");
        this.twitterServiceStub = ServiceBuilder.create().buildTwitter(valuesConfiguration).build();
        Assert.assertFalse(twitterServiceStub.isServiceOk());
    }

//    Sí colocó una configuración correcta de la cuenta  (apiKey, apiSecrectKey, tokenKey y tokenSecretKey: válidos) , la consulta si está habilitado el servicio devuelve true.
    @Test
    public void accountTwitterInvalid_thenIsServiceOk() {
        setValuesAccountUser("ok");
        this.twitterServiceStub = ServiceBuilder.create().buildTwitter(valuesConfiguration).build();
        Assert.assertTrue(twitterServiceStub.isServiceOk());
    }
//    Sí pido la data con el servicio Ok , devuelvo una lista con 2 items (Ids: 1, 2).
    @Test
    public void getListGeneral_whenIsServiceOk_thenReturnListWithSizeTwo() {
        List<TwitterObjectDto> list = twitterServiceStub.getData();
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getClass(),TwitterObjectDto.class);
        Assert.assertEquals(list.get(1).getClass(),TwitterObjectDto.class);
    }

//    Sí pido la data para el servicio general, se devuelve una lista con objetos del tipo InformationDto con dos elementos (Ids, 1, 2, Origen: Twitter).
    @Test
    public void getListGeneral_whenIsServiceOk_thenReturnListInformationDtoWithSizeTwo() {
        List<InformationDto> informationDtoList = twitterServiceStub.getInformation(twitterServiceStub.getData());
        Assert.assertEquals(informationDtoList.size(), 2);
        Assert.assertEquals(informationDtoList.get(0).getClass(), InformationDto.class);
        Assert.assertEquals(informationDtoList.get(1).getClass(), InformationDto.class);
    }

//    Si el valor de la configuración de cantidad de respuesta es 1 devuelvo 1 elemento.
    @Test
    public void getData_whenSizeReturnIsOne_thenReturnOneElement() {
        this.twitterServiceStub.setValueConfiguration("twitter.response.count", "1");
        List<TwitterObjectDto> list = twitterServiceStub.getData();
        Assert.assertEquals(list.size(), 1);
    }
//    Si el valor de la configuración de cantidad de respuesta es 2 devuelvo 2 elementos.
    @Test
    public void getData_whenSizeReturnIsTwo_thenReturnTwoElement() {
        this.twitterServiceStub.setValueConfiguration("twitter.response.count", "2");
        List<TwitterObjectDto> list = twitterServiceStub.getData();
        Assert.assertEquals(list.size(), 2);
    }
//    Si el valor de la configuración de cantidad de respuesta es 0 devuelvo 2 elemento.
    @Test
    public void getData_whenSizeReturnIsZero_thenReturnTwoElement() {
        this.twitterServiceStub.setValueConfiguration("twitter.response.count", "0");
        List<TwitterObjectDto> list = twitterServiceStub.getData();
        Assert.assertEquals(list.size(), 2);
    }

    // Funciones auxiliares
    private void setValuesAccountUser(String gral) {
        valuesConfiguration.put("apiKey",gral);
        valuesConfiguration.put("apiSecrectKey",gral);
        valuesConfiguration.put("tokenKey",gral);
        valuesConfiguration.put("tokenSecretKey",gral);
    }

}
