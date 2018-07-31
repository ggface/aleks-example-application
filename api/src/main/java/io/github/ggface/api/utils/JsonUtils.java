package io.github.ggface.api.utils;

import com.google.gson.JsonElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с парсерами
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class JsonUtils {

    private JsonUtils() {
    }

    public static String string(JsonElement json, String path) {
        JsonElement element = path(json, path);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsString();
        }
        return "";
    }

    public static String string(JsonElement json, Object... segments) {
        JsonElement element = segments(json, segments);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsString();
        }
        return "";
    }

    public static int integer(JsonElement json, String path) {
        JsonElement element = path(json, path);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsInt();
        }
        return 0;
    }

    public static int integer(JsonElement json, Object... segments) {
        JsonElement element = segments(json, segments);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsInt();
        }
        return 0;
    }

    public static long asLong(JsonElement json, String path) {
        JsonElement element = path(json, path);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsLong();
        }
        return 0;
    }

    public static long asLong(JsonElement json, Object... segments) {
        JsonElement element = segments(json, segments);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsLong();
        }
        return 0;
    }

    public static boolean bool(JsonElement json, String path) {
        JsonElement element = path(json, path);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsBoolean();
        }
        return false;
    }

    public static boolean bool(JsonElement json, Object... segments) {
        JsonElement element = segments(json, segments);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsBoolean();
        }
        return false;
    }

    public static BigDecimal bigDecimal(JsonElement json, String path) {
        JsonElement element = path(json, path);
        if (PojoUtils.isNotNull(element) && !element.isJsonNull()) {
            return element.getAsBigDecimal();
        }
        return BigDecimal.ZERO;
    }

    @SuppressWarnings("unchecked")
    public static <T extends JsonElement> T path(JsonElement json, String path) {
        List<Object> segments = new ArrayList<>();
        for (String segment : path.split("\\.")) {
            if (segment.charAt(segment.length() - 1) == ']') {
                segments.add(segment.substring(0, segment.length() - 3));
                segments.add(Character.getNumericValue(segment.charAt(segment.length() - 2)));
            } else {
                segments.add(segment);
            }
        }
        return segments(json, segments.toArray());
    }

    @SuppressWarnings("unchecked")
    public static <T extends JsonElement> T segments(JsonElement json, Object... segments) {
        JsonElement element = json;
        for (Object segment : segments) {
            if (segment == null) {
                break;
            } else if (segment instanceof String) {
                element = element.getAsJsonObject().get((String) segment);
            } else if (segment instanceof Integer) {
                Integer position = (Integer) segment;
                if (element.getAsJsonArray().size() <= position) {
                    element = null;
                } else {
                    element = element.getAsJsonArray().get(position);
                }
            }
        }
        return (T) element;
    }
}