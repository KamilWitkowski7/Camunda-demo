package pl.xyz.demo.eventaggregator.fields;

import java.util.Map;

public class FieldsUtil {
    public static String getString(Map<String, Object> content, Fields field) {
        return (String) content.get(field.toString());
    }
    public static Long getLong(Map<String, Object> content, Fields field) {
        return (Long) content.get(field.toString());
    }
}
