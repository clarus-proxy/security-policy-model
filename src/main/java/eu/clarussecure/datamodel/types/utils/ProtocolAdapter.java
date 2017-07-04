package eu.clarussecure.datamodel.types.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import eu.clarussecure.datamodel.types.Protocol;
import java.io.IOException;

// This class implements the gson serialization for Protocols
public class ProtocolAdapter extends TypeAdapter<Protocol> {

    @Override
    public void write(JsonWriter writer, Protocol t) throws IOException {
        if (t == null) {
            writer.nullValue();
            return;
        }
        writer.value(t.getProtocolName());
    }

    @Override
    public Protocol read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        Protocol.initialize();
        return new Protocol(reader.nextString());
    }
}
