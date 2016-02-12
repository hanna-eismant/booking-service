package com.epam.spring.core.shared;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.joda.time.LocalDateTime;

import java.io.IOException;

public class LocalDateTimeGSONAdapter extends TypeAdapter<LocalDateTime> {

    @Override
    public void write(final JsonWriter jsonWriter, final LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.toString());
    }

    @Override
    public LocalDateTime read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }

        String string = jsonReader.nextString();
        return LocalDateTime.parse(string);
    }
}
