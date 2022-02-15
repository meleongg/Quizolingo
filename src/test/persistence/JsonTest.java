package persistence;

import model.Flashcard;

import static org.junit.jupiter.api.Assertions.*;

// class inspired by JsonSerializationDemo (link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonTest {
    protected void checkFlashcard(String phrase, String translation, Integer proficiencyRating, Flashcard flashcard) {
        assertEquals(phrase, flashcard.getPhrase());
        assertEquals(translation, flashcard.getTranslation());
        assertEquals(proficiencyRating, flashcard.getProficiencyRating());
    }
}
