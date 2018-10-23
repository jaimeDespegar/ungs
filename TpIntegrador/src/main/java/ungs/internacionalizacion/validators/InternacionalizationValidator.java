package ungs.internacionalizacion.validators;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import ungs.internacionalizacion.LenguajeConfigurationDto;
import java.util.List;
import java.util.Map;

public class InternacionalizationValidator {


    public boolean isValidate(List<LenguajeConfigurationDto> list) {
        return CollectionUtils.isNotEmpty(list) && isSameSizeValuesAtAllLenguajes(list) && isValidValues(list);
    }

    public boolean isValidValues(List<LenguajeConfigurationDto> list) {
        return list.stream()
                   .anyMatch(i-> StringUtils.isNotBlank(i.getLenguaje()) && !isEmptyValues(i.getValues()));
    }

    private boolean isEmptyValues(Map mapValues) {
        return mapValues.keySet().stream().anyMatch(key   -> StringUtils.isBlank((String)key)) ||
               mapValues.values().stream().anyMatch(value -> StringUtils.isBlank((String)value));
    }

    private boolean isSameSizeValuesAtAllLenguajes(List<LenguajeConfigurationDto> listLenguajes) {
        Integer sizeFirstValues = listLenguajes.get(0).getValues().size();
        return listLenguajes.stream().anyMatch(i->i.getValues().size()!=sizeFirstValues);
    }

}