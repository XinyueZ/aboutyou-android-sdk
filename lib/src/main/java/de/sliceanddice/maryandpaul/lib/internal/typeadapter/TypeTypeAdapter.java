package de.sliceanddice.maryandpaul.lib.internal.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.sliceanddice.maryandpaul.lib.enums.Type;

public class TypeTypeAdapter implements JsonSerializer<Type> {

    @Override
    public JsonElement serialize(Type src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
