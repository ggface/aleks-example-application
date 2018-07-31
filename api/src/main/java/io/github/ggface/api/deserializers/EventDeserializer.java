package io.github.ggface.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import io.github.ggface.api.beans.EventBean;
import io.github.ggface.api.utils.JsonUtils;

/**
 * Десериализатор для {@link EventBean}
 *
 * @author Ivan Novikov on 2018-07-31.
 */
public class EventDeserializer implements JsonDeserializer<EventBean> {

    @Override
    public EventBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new EventBean(
                JsonUtils.string(json, "name"),
                new Date(JsonUtils.asLong(json, "startTime")),
                new Date(JsonUtils.asLong(json, "endTime")),
                JsonUtils.string(json, "teacher"),
                JsonUtils.string(json, "place"),
                JsonUtils.string(json, "description"),
                JsonUtils.integer(json, "weekDay"));
    }
}