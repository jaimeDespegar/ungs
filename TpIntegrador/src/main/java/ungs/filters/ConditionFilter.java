package ungs.filters;

public class ConditionFilter {

    private String condition;

    public ConditionFilter(String condition) {
        this.condition = condition;
    }

    public ConditionFilter() {
        this.condition = "";
    }

    public void addCondition(String condition) {
        this.condition += condition;
    }

    public String getValue() {
        return this.condition;
    }

}