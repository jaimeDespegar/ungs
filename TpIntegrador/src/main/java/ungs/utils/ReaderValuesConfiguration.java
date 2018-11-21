package ungs.utils;

import com.google.common.collect.Maps;
import ungs.utils.exceptions.ConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ReaderValuesConfiguration {

    private Properties properties;
    private Map<String, String> mapValues = Maps.newHashMap();

    public ReaderValuesConfiguration(String path) {
        this.properties = new Properties();

        try {
            properties.load((new FileReader(path)));
            mapValues.putAll(Maps.fromProperties(properties));
        }
        catch (FileNotFoundException e) {
            throw new ConfigurationException(String.format("El archivo de Configuracion %s no existe. ", path), e);
        }
        catch (IOException e) {
            throw new ConfigurationException("Error al leer el archivo de configuracion: " + path, e);
        }
    }

    public Map<String, String> getValues() {
        return mapValues;
    }

    public static Map<String, String> getValuesProperties(String pathFile) {
        return new ReaderValuesConfiguration(pathFile).mapValues;
    }

}