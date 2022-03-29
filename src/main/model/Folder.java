package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a folder of language flashcards
public class Folder implements Writable {
    private List<Flashcard> flashcards;

    // EFFECTS: initializes folder with 0 flashcards
    public Folder() {
        this.flashcards = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("flashcards", flashcardsToJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: adds flashcard to list of flashcards if a flashcard does not already
    //          exist with the same phrase
    public void addFlashcard(Flashcard flashcard) {
        if (!checkPhraseAlreadyExists(flashcard.getPhrase())) {
            this.flashcards.add(flashcard);
            String phrase = flashcard.getPhrase();
            EventLog.getInstance().logEvent(new Event("Flashcard with phrase: "
                    + "\"" + phrase + "\"" + " added to folder!"));
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the given flashcard from the folder
    //          if flashcard exists
    public void removeFlashcard(Flashcard flashcard) {
        if (this.flashcards.contains(flashcard)) {
            this.flashcards.remove(flashcard);
            String phrase = flashcard.getPhrase();
            EventLog.getInstance().logEvent(new Event("Flashcard with phrase: "
                    + "\"" + phrase + "\"" + " removed from folder!"));
        }
    }

    // EFFECTS: produces true if a flashcard with the same phrase
    //          already exists in the flashcards field, else false
    public boolean checkPhraseAlreadyExists(String phrase) {
        for (Flashcard flashcard : this.flashcards) {
            if (flashcard.getPhrase().equals(phrase)) {
                return true;
            }
        }
        return false;
    }

    private JSONArray flashcardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard flashcard : this.flashcards) {
            jsonArray.put(flashcard.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns flashcard with given phrase as its phrase if
    //          it exists, else returns a dummy flashcard
    public Flashcard getFlashcardByPhrase(String phrase) {
        for (Flashcard flashcard : this.flashcards) {
            if (flashcard.getPhrase().equals(phrase)) {
                return flashcard;
            }
        }

        return new Flashcard("", "", 1);
    }

    public List<Flashcard> getFlashcards() {
        return this.flashcards;
    }
}
