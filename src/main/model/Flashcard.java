package model;

// Represents a flashcard that has a phrase in the language the user is wishing to learn,
// a translation in the language the user is already proficient in,
// and a proficiency rating for the phrase on a scale of 1 to 5
public class Flashcard {
    private String phrase;
    private String translation;
    private int proficiencyRating;

    /*
    * REQUIRES: phrase and translation have non-zero lengths, proficiencyRating is within [1, 5]
    * EFFECTS: initializes a flashcard instance with the given parameters
    */
    public Flashcard(String phrase, String translation, int proficiencyRating) {
        this.phrase = phrase;
        this.translation = translation;
        this.proficiencyRating = proficiencyRating;
    }

    // REQUIRES: phrase has non-zero length
    // MODIFIES: this
    // EFFECTS: updates the flashcard's phrase
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    // REQUIRES: translation has non-zero length
    // MODIFIES: this
    // EFFECTS: updates the flashcard's translation
    public void setTranslation(String translation) {
        this.translation = translation;
    }

    // REQUIRES: rating within [1, 5]
    // MODIFIES: this
    // EFFECTS: updates the flashcard's current proficiencyRating to be rating
    public void setProficiencyRating(int rating) {
        this.proficiencyRating = rating;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public String getTranslation() {
        return this.translation;
    }

    public int getProficiencyRating() {
        return this.proficiencyRating;
    }
}
