package ungs.internacionalizacion;

import java.util.List;

public class ResourceBundleLenguaje {

    private List<LenguajeConfigurationDto> list;

    public ResourceBundleLenguaje(LenguajeDto lenguajeDto) {
        
    }

    public LenguajeConfigurationDto getLenguajeConfigurationDTO(LenguajeDto lenguajeDto) {
        return this.list.stream().filter(l -> l.getLenguaje().equalsIgnoreCase(lenguajeDto.getLenguaje()))
                                 .findFirst()
                                 .orElseThrow(RuntimeException::new);
    }

}