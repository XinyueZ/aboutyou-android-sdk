package de.aboutyou.internal.typeadapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.aboutyou.internal.util.ErrorAwareJsonDeserializer;
import de.aboutyou.models.Suggest;

public class SuggestTypeAdapter extends ErrorAwareJsonDeserializer<Suggest> {

    @Override
    public Suggest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Suggest suggest = new Suggest();
        boolean isErrorObject = false;

        try {
            List<String> objects = context.deserialize(json, new TypeToken<ArrayList<String>>() {
            }.getType());

            Field delegate = Suggest.class.getDeclaredField("delegate");
            delegate.setAccessible(true);
            delegate.set(suggest, objects);
        } catch (JsonParseException e) {
            isErrorObject = true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new JsonParseException("");
        }

        if (isErrorObject) {
            deserializeError(json, suggest, context);
        }

        return suggest;
    }
}
