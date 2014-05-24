package de.aboutyou.internal.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import de.aboutyou.models.BaseModel;

public abstract class ErrorAwareJsonDeserializer<T extends BaseModel> implements JsonDeserializer<T> {

    protected void deserializeError(JsonElement json, T target, JsonDeserializationContext context) {
        JsonObject errorObject = json.getAsJsonObject();

        Field[] fields = target.getClass().getSuperclass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Type fieldType = field.getType();

            String serializedName;
            if (field.isAnnotationPresent(SerializedName.class)) {
                SerializedName annotation = field.getAnnotation(SerializedName.class);
                serializedName = annotation.value();
            } else {
                serializedName = fieldName;
            }

            try {
                JsonElement elem = errorObject.get(serializedName);
                if (elem != null) {
                    Object fieldData = context.deserialize(elem, fieldType);

                    field.setAccessible(true);
                    field.set(target, fieldData);
                }
            } catch (IllegalAccessException e) {
                throw new JsonParseException(String.format("Error parsing %s into %s.", serializedName, fieldName));
            }
        }
    }

}
