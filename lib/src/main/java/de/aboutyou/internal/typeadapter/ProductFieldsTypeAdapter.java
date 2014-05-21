package de.aboutyou.internal.typeadapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import de.aboutyou.enums.ProductFields;

public class ProductFieldsTypeAdapter implements JsonSerializer<ProductFields> {

    @Override
    public JsonElement serialize(ProductFields src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

}
