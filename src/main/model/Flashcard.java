package model;

// Represents a flashcard that has a phrase in the language the user is wishing to learn,
// a translation in the language the user is already proficient in,
// and a proficiency rating within [1, 5]
public class Flashcard {

    /*
    * REQUIRES: phrase and translation have non-zero lengths, proficiencyRating is [1, 5]
    * EFFECTS: intializes a flashcard instance with the given parameters
    */
    public Flashcard(String phrase, String translation, int rating) {
        // stub
    }

    // REQUIRES: phrase has non-zero length
    // MODIFIES: this
    // EFFECTS: updates the flashcard's phrase
    public void setPhrase(String phrase) {
        // stub
    }

    // REQUIRES: translation has non-zero length
    // MODIFIES: this
    // EFFECTS: updates the flashcard's translation
    public void setTranslation(String translation) {
        // stub
    }

    // REQUIRES: rating [1, 5]
    // MODIFIES: this
    // EFFECTS: updates the flashcard's current proficiencyRating to be rating
    public void setProficiencyRating(int rating) {
        // stub
    }

    public String getPhrase() {
        return "";
    }

    public String getTranslation() {
        return "";
    }

    public int getProficiencyRating() {
        return 0;
    }
}
