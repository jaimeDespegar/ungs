package ungs.utils;

public class TimeUtils {

    public static Long getMinutes(Long value) {
        if (value != null) {
            return value * 1000 * 60;
        }
        throw new IllegalArgumentException("Value Not Valid");
    }

}