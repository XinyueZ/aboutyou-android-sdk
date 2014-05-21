package de.aboutyou.internal.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import de.aboutyou.enums.FacetGroup;

public class FacetGroupTypeAdapter implements JsonSerializer<FacetGroup> {

    @Override
    public JsonElement serialize(FacetGroup src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }

}