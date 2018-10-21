package ungs.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Optional;

public class JsonMapper {

    private static JsonMapper JSONPARSER;
    private Gson gson;

    private JsonMapper() {
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    public static JsonMapper getMapper() {
        JSONPARSER = Optional.ofNullable(JSONPARSER).orElse(new JsonMapper());
        return JSONPARSER;
    }

    public <T> T getValue(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> T getValueFromXml(InputStream xml, Type type) {
        try {
            JSONObject xmlToJsonObject = XML.toJSONObject(new String(IOUtils.toByteArray(xml)));
            String jsonString = xmlToJsonObject.toString();
            return getValue(jsonString, type);
        } catch (IOException e) {
            throw new RuntimeException("Error al mapear XML to JSON", e);
        }
    }

}
