package shared.gson;

import com.google.gson.*;

import java.lang.reflect.Type;

public class Serializer<T> implements JsonSerializer<T> {

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject retValue = new JsonObject();
        String className = src.getClass().getName();
        retValue.addProperty("CLASSNAME", className);
        JsonElement elem = context.serialize(src);
        retValue.add("INSTANCE", elem);
        return retValue;
    }
}
