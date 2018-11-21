package criteriosDeAceptacion.first.iteration;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import servicesStub.TwitterServiceStub;
import ungs.dto.TwitterObjectDto;
import ungs.model.Configuration;
import ungs.model.InformationDto;
import ungs.utils.exceptions.ConfigurationException;
import java.util.List;
import java.util.Map;

public class UserStoryOne {

//    Sí se setea el archivo twitter-noexists.properties como configuracion en el servicio, la aplicación retorna una ConfigurationException
//    con el mensaje “El archivo de configuración twitter.properties no existe”.

    private TwitterServiceStub twitterServiceStub;
    private Map<String,String> valuesConfiguration = Maps.newHashMap();


    @BeforeClass
    public void init() {
        String path = "src/test/resources/test-files/services/twitter/twitter-exists.properties";
        this.twitterServiceStub = new TwitterServiceStub(new Configuration(path));
    }



    @Test(expectedExceptions = ConfigurationException.class)
    public void createServiceTwitter_withConfigurationInvalid() {
        this.twitterServiceStub = new TwitterServiceStub(new Configuration("twitter-noexistis.properties"));
    }

//    Sí se setea el archivo twitter-exists.properties se como configuración en el servicio , se crea correctamente el servicio.
    @Test
    public void createServiceTwitter_withConfigurationValid() {
        String path = "src/test/resources/test-files/services/twitter/twitter-exists.properties";
        this.twitterServiceStub = new TwitterServiceStub(new Configuration(path));
        Assert.assertNotNull(twitterServiceStub.getConfiguration());
    }


//    Sí colocó una configuración inválida de la cuenta  (apiKey incorrecta) , la consulta sí está habilitado el servicio devuelve false.
    @Test
    public void accountTwitterInvalid_thenIsServiceNotOk() {
        this.setValuesAccountUser("invalid");
        twitterServiceStub.setConfiguration(new Configuration(valuesConfiguration));
        Assert.assertFalse(twitterServiceStub.isServiceOk());
    }

//    Sí colocó una configuración correcta de la cuenta  (apiKey, apiSecrectKey, tokenKey y tokenSecretKey: válidos) , la consulta si está habilitado el servicio devuelve true.
    @Test
    public void accountTwitterInvalid_thenIsServiceOk() {
        setValuesAccountUser("ok");
        twitterServiceStub.setConfiguration(new Configuration(valuesConfiguration));
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
