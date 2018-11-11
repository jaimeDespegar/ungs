package ungs.internacionalizacion;

import com.fasterxml.jackson.core.type.TypeReference;
import ungs.utils.ConfigUtils;
import ungs.utils.JsonMapper;
import java.util.List;

public class ResourceBundleLenguaje {

    private List<LenguajeConfiguration> list;
    private JsonMapper mapper = JsonMapper.getMapper();

    public ResourceBundleLenguaje() {
        this.list = mapper.getValue(mapper.toJson(ConfigUtils.ERRORS_FILE), new TypeReference<List<LenguajeConfiguration>>() {}.getClass());
    }

    public LenguajeConfiguration getLenguajeConfigurationDTO(LenguajeValue lenguajeDto) {
        return this.list.stream().filter(l -> l.getLenguaje().equalsIgnoreCase(lenguajeDto.getLenguaje()))
                                 .findFirst()
                                 .orElseThrow(RuntimeException::new);
    }

}