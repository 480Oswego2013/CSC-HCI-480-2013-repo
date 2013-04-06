/*
 * Copyright 2013 State University of New York at Oswego
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.oswego.csc480_hci521_2013.shared.h2o.json.Inspect.Row;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 */
public class InspectRowDeserializer implements JsonDeserializer<Row> {

    @Override
    public Row deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject json = je.getAsJsonObject();
        int row = json.get("row").getAsInt();
        HashMap<String, String> data = new HashMap<String, String>();
        for (Entry<String, JsonElement> entry : json.entrySet()) {
            data.put(entry.getKey(), entry.getValue().getAsString());
        }
        return new Row(row, data);
    }
}
