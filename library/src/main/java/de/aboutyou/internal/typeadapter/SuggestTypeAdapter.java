package de.aboutyou.internal.typeadapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.aboutyou.models.BaseModel;
import de.aboutyou.models.Suggest;

public class SuggestTypeAdapter implements JsonDeserializer<Suggest> {

    @Override
    public Suggest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            Suggest suggest = new Suggest();
            boolean isErrorObject = false;

            try {
                List<String> objects = context.deserialize(json, new TypeToken<ArrayList<String>>() {}.getType());

                Field delegate = Suggest.class.getDeclaredField("delegate");
                delegate.setAccessible(true);
                delegate.set(suggest, objects);
            } catch (JsonParseException e) {
                isErrorObject = true;
            }

            if (isErrorObject) {
                JsonObject errorObject = json.getAsJsonObject();
                List<String> errorMessagesObj = context.deserialize(errorObject.get("error_message"), new TypeToken<ArrayList<String>>() {}.getType());
                int errorCodeObj = context.deserialize(errorObject.get("error_code"), Integer.class);

                Field errorMessage = Suggest.class.getSuperclass().getDeclaredField("errorMessages");
                errorMessage.setAccessible(true);
                errorMessage.set(suggest, errorMessagesObj);

                Field errorCode = Suggest.class.getSuperclass().getDeclaredField("errorCode");
                errorCode.setAccessible(true);
                errorCode.set(suggest, errorCodeObj);
            }

            return suggest;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}
