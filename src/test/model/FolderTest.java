package model;

import javafx.util.converter.FloatStringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
QUESTIONS:

Why aren't my tests running?
- QuizolingoApp should be calling for the creation of a new folder class
- folder class should be inside model with Flashcard
- all tests & setup method should be public

UpdateProficiencyRating() REQUIRES vs EFFECTS for diff rating?
- do I need to double test UpdateProficiencyRating? Or leave only in 1 test suite?
- AND if flashcard exists?
Should addFlashcard just take it 1 flashcard as input?
Should I test rating change from 1 to 1?

*/

class FolderTest {
    private Folder testFolder;
    private Flashcard bonjourFlashcard;
    private Flashcard bonjourFlashcardDiffRating;
    private Flashcard bonsoirFlashcard;
    private Flashcard bonsoirFlashcardDiffRating;
    private Flashcard savonFlashcard;

    @BeforeEach
    public void setup() {
        testFolder = new Folder();
        bonjourFlashcard = new Flashcard("bonjour", "hello", 3);
        bonjourFlashcardDiffRating = new Flashcard("bonjour", "hello", 4);
        bonsoirFlashcard = new Flashcard("bonsoir", "good evening", 4);
        bonsoirFlashcardDiffRating = new Flashcard("bonsor", "good evening", 5);
        savonFlashcard = new Flashcard("savon", "soap", 4);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, this.testFolder.getFlashcards().size());
    }

    // TODO: test runFolder? how to test if app responds correctly to input?

    @Test
    public void testAddOneFlashcard() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testAddOneFlashcardWithRating1() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, testFolder.getFlashcards().size());
    }

    @Test
    public void testAddOneFlashcardWithRating5() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testAddTwoFlashcards() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testAddFlashcardAlreadyExists() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testAddFlashcardsAndAlreadyExists() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.savonFlashcard);
        assertEquals(3, this.testFolder.getFlashcards());
    }

    @Test
    public void testRemoveOneFlashcard() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.bonjourFlashcard);
        assertEquals(0, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testRemoveTwoFlashcards() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.bonsoirFlashcard);
        assertEquals(0, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testRemoveFlashcardNotExists() {
        assertEquals(0, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.bonsoirFlashcard);
        assertEquals(0, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testRemoveFlashcardsAndNotExists() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.savonFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.removeFlashcard(this.bonsoirFlashcard);
        assertEquals(0, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testUpdateProficiencyRatingTo1() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.testFolder.updateProficiencyRating(this.bonjourFlashcard, 1);
        assertEquals(1, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testUpdateProficiencyRatingTo5() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.testFolder.updateProficiencyRating(this.bonjourFlashcard, 5);
        assertEquals(5, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testUpdateProficiencyRatingToSameNumber() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(4, this.bonjourFlashcard.getProficiencyRating());
        this.testFolder.updateProficiencyRating(this.bonjourFlashcard, 4);
        assertEquals(4, this.bonjourFlashcard.getProficiencyRating());
    }

    @Test
    public void testAddFlashcardsAndRemoveFlashcardsAndUpdateProficiency() {
        assertEquals(0, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        assertEquals(3, this.bonjourFlashcard.getProficiencyRating());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        assertEquals(3, this.bonsoirFlashcard.getProficiencyRating());
        this.testFolder.updateProficiencyRating(this.bonjourFlashcard, 2);
        assertEquals(2, this.bonjourFlashcard.getProficiencyRating());
        this.testFolder.removeFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.updateProficiencyRating(this.bonsoirFlashcard, 2);
        assertEquals(2, this.bonsoirFlashcard.getProficiencyRating());
        this.testFolder.removeFlashcard(this.bonsoirFlashcard);
        assertEquals(0, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testAddSameFlashcardsDifferentRatings() {
        assertEquals(0, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcardDiffRating);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcardDiffRating);
        assertEquals(0, this.testFolder.getFlashcards().size());
    }
}