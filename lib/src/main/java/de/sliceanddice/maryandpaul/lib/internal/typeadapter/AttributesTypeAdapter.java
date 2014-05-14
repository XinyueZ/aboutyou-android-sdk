package de.sliceanddice.maryandpaul.lib.internal.typeadapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.models.Attributes;

public class AttributesTypeAdapter implements JsonDeserializer<Attributes> {

    private static final String PREFIX = "attributes_";

    @Override
    public Attributes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Attributes attributes = new Attributes();

        JsonObject jsonObject = json.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            int facetGroupId = Integer.parseInt(entry.getKey().replace(PREFIX, ""));
            List<Long> facetIds = context.deserialize(entry.getValue(), new TypeToken<ArrayList<Long>>() {}.getType());
            attributes.put(facetGroupId, facetIds);
        }

        return attributes;
    }
}
