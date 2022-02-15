package persistence;

import model.Flashcard;
import model.Folder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// class inspired by JsonSerializationDemo (link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonReader {
    private String source;

    // EFFECTS: creates new JsonReader instance to read data from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: translates folder from source file and returns it;
    // throws IOExceptions if an error occurs reading data from file
    public Folder read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFolder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses folder from JSON object and returns it
    private Folder parseFolder(JSONObject jsonObject) {
        Folder folder = new Folder();
        addFlashcards(folder, jsonObject);
        return folder;
    }

    // MODIFIES: folder
    // EFFECTS: parses flashcards from JSON object and adds them to the folder
    private void addFlashcards(Folder folder, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcards");
        for (Object json : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) json;
            addFlashcard(folder, nextFlashcard);
        }
    }

    // MODIFIES: folder
    // EFFECTS: parses flashcard from JSON Object and adds it to the folder
    private void addFlashcard(Folder folder, JSONObject jsonObject) {
        String phrase = jsonObject.getString("phrase");
        String translation = jsonObject.getString("translation");
        Integer proficiencyRating = jsonObject.getInt("proficiencyRating");
        Flashcard flashcard = new Flashcard(phrase, translation, proficiencyRating);
        folder.addFlashcard(flashcard);
    }
}
