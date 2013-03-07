package edu.oswego.csc480_hci521_2013.h2owrapper.json.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.InspectRow;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 */
public class InspectRowDeserializer  implements JsonDeserializer<InspectRow>
{
    @Override
    public InspectRow deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
        JsonObject json = je.getAsJsonObject();
        int row = json.get("row").getAsInt();
        Map<String, String> data = new HashMap<String, String>();
        for (Entry<String, JsonElement> entry: json.entrySet()) {
            data.put(entry.getKey(), entry.getValue().getAsString());
        }
        return new InspectRow(row, data);
    }
}
