package com.epam.spring.core.shared;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.joda.time.LocalDate;

import java.io.IOException;

public class LocalDateGSONAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
        jsonWriter.value(localDate.toString());
    }

    @Override
    public LocalDate read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }

        String string = jsonReader.nextString();
        return LocalDate.parse(string);
    }
}
