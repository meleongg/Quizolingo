package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static ui.QuizolingoApp.MIN_PROFICIENCY_RATING;
import static ui.QuizolingoApp.MAX_PROFICIENCY_RATING;

public class FlashcardTest {
    private Flashcard bonjourFlashcard;

    @BeforeEach
    public void setup() {
        bonjourFlashcard = new Flashcard("bonjour", "hello", 3);
    }

    @Test
    public void testConstructor() {
        assertEquals("bonjour", this.bonjourFlashcard.getPhrase());
        assertEquals("hello", this.bonjourFlashcard.getTranslation());
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testSetPhrase() {
        assertEquals("bonjour", this.bonjourFlashcard.getPhrase());
        this.bonjourFlashcard.setPhrase("bonsoir");
        assertEquals("bonsoir", this.bonjourFlashcard.getPhrase());
    }

    @Test
    public void testSetSamePhrase() {
        assertEquals("bonjour", this.bonjourFlashcard.getPhrase());
        this.bonjourFlashcard.setPhrase("bonjour");
        assertEquals("bonjour", this.bonjourFlashcard.getPhrase());
    }

    @Test
    public void testSetTranslation() {
        assertEquals("hello", this.bonjourFlashcard.getTranslation());
        this.bonjourFlashcard.setTranslation("hello!");
        assertEquals("hello!", this.bonjourFlashcard.getTranslation());
    }

    @Test
    public void testSetSameTranslation() {
        assertEquals("hello", this.bonjourFlashcard.getTranslation());
        this.bonjourFlashcard.setTranslation("hello");
        assertEquals("hello", this.bonjourFlashcard.getTranslation());
    }

    @Test
    public void testSetProficiencyRatingToMin() {
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.bonjourFlashcard.setProficiencyRating(MIN_PROFICIENCY_RATING);
        assertEquals(MIN_PROFICIENCY_RATING, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testSetProficiencyRatingToMax() {
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.bonjourFlashcard.setProficiencyRating(MAX_PROFICIENCY_RATING);
        assertEquals(MAX_PROFICIENCY_RATING, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testSetProficiencyRatingToSame() {
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.bonjourFlashcard.setProficiencyRating(3);
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
    }
}
