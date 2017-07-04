package eu.clarussecure.datamodel.types.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import eu.clarussecure.datamodel.types.Module;
import java.io.IOException;

// This class implements the gson serialization for Modules
public class ModuleAdapter extends TypeAdapter<Module> {

    @Override
    public void write(JsonWriter writer, Module t) throws IOException {
        if (t == null) {
            writer.nullValue();
            return;
        }
        writer.value(t.getClarusModuleName());
    }

    @Override
    public Module read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return new Module(reader.nextString());
    }
}
