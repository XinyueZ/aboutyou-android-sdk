package de.aboutyou.internal.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import de.aboutyou.enums.Direction;

public class DirectionTypeAdapter implements JsonSerializer<Direction> {

    @Override
    public JsonElement serialize(Direction src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
