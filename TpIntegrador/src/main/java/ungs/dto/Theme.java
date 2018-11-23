package ungs.dto;

import com.google.common.collect.Lists;
import java.util.List;

public class Theme {

    private String value;
    private List<String> subValues;

    public Theme() {
        this.value = "";
        this.subValues = Lists.newArrayList();
    }

    public Theme(String value) {
        this.value = value;
        this.subValues = Lists.newArrayList();
    }

    public Theme(String value, List<String> subValues) {
        this.value = value;
        this.subValues = subValues;
    }

    public List<String> getSubValues() {
        return subValues;
    }

    public void setSubValues(List<String> subValues) {
        this.subValues = subValues;
    }

    public String getValue() {
        return value;
    }
}