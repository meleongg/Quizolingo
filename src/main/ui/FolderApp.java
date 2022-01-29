package ui;

import model.Flashcard;

import java.util.ArrayList;
import java.util.List;

// Folder application
public class FolderApp {

    // EFFECTS: runs the folder application
    public FolderApp() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFolder() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: creates a new flash card for a given phrase with the given translation
    private void addFlashcard(String phrase, String translation) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes the given flashcard from the folder
    private void removeFlashcard(Flashcard flashcard) {
        // stub
    }

    // REQUIRES: given integer must be >= 1 and <= 5
    // MODIFIES: this, Flashcard
    // EFFECTS: updates the proficiency rating of the given flashcard to be the given integer
    private void updateProficiencyRating(Flashcard flashcard, int rating) {
        // stub
    }

    private List<Flashcard> getFlashcards() {
        return new ArrayList();
    }
}
