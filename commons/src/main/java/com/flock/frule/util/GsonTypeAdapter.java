package com.flock.frule.util;

import com.flock.frule.model.jsondata.*;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

//import com.google.gson.*;

public class GsonTypeAdapter extends TypeAdapter<JsonType> {

    @Override
    public void write(JsonWriter out, JsonType value) throws IOException {
        if (value == null || value.isNull()) {
            out.nullValue();
        } else if (value.isPrimitive()) {
            JsonPrimitive primitive = value.asPrimitive();
            if (primitive.isNumber()) {
                out.value(primitive.getAsNumber());
            } else if (primitive.isBoolean()) {
                out.value(primitive.getAsBoolean());
            } else {
                out.value(primitive.getAsString());
            }

        } else if (value.isArray()) {
            out.beginArray();
            JsonArray array = value.asArray();
            int size = array.size();
            for(int i=0;i<size;++i)
                write(out,array.get(i));
//            for (JsonElement e : value.getAsJsonArray()) {
//                write(out, e);
//            }
            out.endArray();

        } else if (value.isObject()) {
            out.beginObject();

            for (Map.Entry<String, JsonType> e : value.asObject().entrySet()) {
                out.name(e.getKey());
                write(out, e.getValue());
            }
            out.endObject();

        } else {
            throw new IllegalArgumentException("Couldn't write " + value.getClass());
        }
    }

    @Override
    public JsonType read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case STRING:
                return new JsonPrimitive(in.nextString());
            case NUMBER:
                String number = in.nextString();
                return new JsonPrimitive(new LazilyParsedNumber(number));
            case BOOLEAN:
                return new JsonPrimitive(in.nextBoolean());
            case NULL:
                in.nextNull();
                return JsonNull.get();
            case BEGIN_ARRAY:
                JsonArray array = new JsonArray();
                in.beginArray();
                while (in.hasNext()) {
                    array.add(read(in));
                }
                in.endArray();
                return array;
            case BEGIN_OBJECT:
                JsonObject object = new JsonObject();
                in.beginObject();
                while (in.hasNext()) {
                    object.put(in.nextName(), read(in));
                }
                in.endObject();
                return object;
            case END_DOCUMENT:
            case NAME:
            case END_OBJECT:
            case END_ARRAY:
            default:
                throw new IllegalArgumentException();
        }
    }
}
