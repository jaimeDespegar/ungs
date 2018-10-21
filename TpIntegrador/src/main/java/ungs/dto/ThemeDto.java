package ungs.dto;

import com.google.common.collect.Lists;
import java.util.List;

public class ThemeDto {

    private String value;
    private List<String> subValues;

    public ThemeDto() {
        this.value = "";
        this.subValues = Lists.newArrayList();
    }

    public ThemeDto(String value, List<String> subValues) {
        this.value = value;
        this.subValues = subValues;
    }

    public void addSubValue(String value) {
        this.subValues.add(value);
    }

    public void removeSubValue(String value) {
        this.subValues.remove(value);
    }

    public List<String> getSubValues() {
        return subValues;
    }

}