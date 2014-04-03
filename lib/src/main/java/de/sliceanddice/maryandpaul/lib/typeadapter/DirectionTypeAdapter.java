package de.sliceanddice.maryandpaul.lib.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import de.sliceanddice.maryandpaul.lib.enums.Direction;

public class DirectionTypeAdapter implements JsonSerializer<Direction> {

    @Override
    public JsonElement serialize(Direction src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
