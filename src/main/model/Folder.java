package model;

import java.util.ArrayList;
import java.util.List;

// Folder application
public class Folder {
    private List<Flashcard> flashcards;

    // EFFECTS: initializes folder with 0 flashcards
    public Folder() {
        this.flashcards = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds flashcard to list of flashcards if a flashcard does not already
    //          exist with the same phrase
    public void addFlashcard(Flashcard flashcard) {
        if (!checkPhraseAlreadyExists(flashcard.getPhrase())) {
            flashcards.add(flashcard);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the given flashcard from the folder
    //          if flashcard exists
    public void removeFlashcard(Flashcard flashcard) {
        if (this.flashcards.contains(flashcard)) {
            flashcards.remove(flashcard);
        }
    }

    // EFFECTS: produces true if a flashcard with the same phrase
    //          already exists in the flashcards field, else false
    public boolean checkPhraseAlreadyExists(String phrase) {
        for (Flashcard flashcard : this.flashcards) {
            if (flashcard.getPhrase() == phrase) {
                return true;
            }
        }
        return false;
    }

    public List<Flashcard> getFlashcards() {
        return this.flashcards;
    }
}
