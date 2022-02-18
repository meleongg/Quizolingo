package persistence;

import org.json.JSONObject;

// interface inspired by JsonSerializationDemo (link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
