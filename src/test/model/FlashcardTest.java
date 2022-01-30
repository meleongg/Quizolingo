package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testSetProficiencyRatingTo1() {
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.bonjourFlashcard.setProficiencyRating(1);
        assertEquals(1, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testSetProficiencyRatingTo5() {
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.bonjourFlashcard.setProficiencyRating(5);
        assertEquals(5, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testSetProficiencyRatingToSame() {
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.bonjourFlashcard.setProficiencyRating(3);
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
    }
}
