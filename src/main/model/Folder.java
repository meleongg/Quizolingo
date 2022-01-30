package model;

import model.Flashcard;

import java.util.ArrayList;
import java.util.List;

// Folder application
public class Folder {

    // EFFECTS: initializes folder with 0 flashcards
    public Folder() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds flashcard to list of flashcards if a flashcard does not already
    //          exist with the same phrase
    public void addFlashcard(Flashcard flashcard) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes the given flashcard from the folder
    //          if flashcard exists
    public void removeFlashcard(Flashcard flashcard) {
        // stub
    }

    // REQUIRES: flashcard must exist and given integer must be >= 1 and <= 5
    // MODIFIES: this, Flashcard
    // EFFECTS: updates the proficiency rating of the given flashcard to be the given integer
    //          if the given integer is different than current integer
    public void updateProficiencyRating(Flashcard flashcard, int rating) {
        // stub
    }

    public List<Flashcard> getFlashcards() {
        return new ArrayList();
    }
}
