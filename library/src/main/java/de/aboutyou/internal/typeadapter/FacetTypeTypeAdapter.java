package de.aboutyou.internal.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import de.aboutyou.enums.FacetType;

public class FacetTypeTypeAdapter implements JsonSerializer<FacetType> {

    @Override
    public JsonElement serialize(FacetType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }

}
