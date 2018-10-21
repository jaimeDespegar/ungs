package ungs.internacionalizacion;

import java.util.Map;

public class LenguajeConfigurationDto {

    private String lenguaje;
    private Map<String, String> values;

    public LenguajeConfigurationDto(String lenguaje, Map<String, String> values) {
        this.lenguaje = lenguaje;
        this.values = values;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

}