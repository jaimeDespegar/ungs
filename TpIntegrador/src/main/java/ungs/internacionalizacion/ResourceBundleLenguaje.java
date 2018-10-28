package ungs.internacionalizacion;

import java.util.List;

public class ResourceBundleLenguaje {

    private List<LenguajeConfiguration> list;

    public ResourceBundleLenguaje(LenguajeValue lenguajeDto) {
        
    }

    public LenguajeConfiguration getLenguajeConfigurationDTO(LenguajeValue lenguajeDto) {
        return this.list.stream().filter(l -> l.getLenguaje().equalsIgnoreCase(lenguajeDto.getLenguaje()))
                                 .findFirst()
                                 .orElseThrow(RuntimeException::new);
    }

}