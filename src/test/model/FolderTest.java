package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        bonsoirFlashcardDiffRating = new Flashcard("bonsoir", "good evening", 5);
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
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.savonFlashcard);
        assertEquals(3, this.testFolder.getFlashcards().size());
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
    public void testAddSameFlashcardsDifferentRatings() {
        assertEquals(0, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonjourFlashcardDiffRating);
        assertEquals(1, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertEquals(2, this.testFolder.getFlashcards().size());
        this.testFolder.addFlashcard(this.bonsoirFlashcardDiffRating);
        assertEquals(2, this.testFolder.getFlashcards().size());
    }

    @Test
    public void testCheckPhraseAlreadyExists() {
        assertFalse(this.testFolder.checkPhraseAlreadyExists("bonjour"));
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertTrue(this.testFolder.checkPhraseAlreadyExists("bonjour"));
    }

    @Test
    public void testCheckPhraseAlreadyExistsWithOtherPhrases() {
        assertFalse(this.testFolder.checkPhraseAlreadyExists("bonjour"));
        this.testFolder.addFlashcard(this.bonsoirFlashcard);
        assertFalse(this.testFolder.checkPhraseAlreadyExists("bonjour"));
        this.testFolder.addFlashcard(this.savonFlashcard);
        assertFalse(this.testFolder.checkPhraseAlreadyExists("bonjour"));
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertTrue(this.testFolder.checkPhraseAlreadyExists("bonjour"));
    }

    @Test
    public void testGetFlashcardByPhraseFlashcardExists() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        assertEquals(this.bonjourFlashcard, this.testFolder.getFlashcardByPhrase("bonjour"));
    }

    @Test
    public void testGetFlashcardByPhraseFlashcardExistsWithOtherFlashcards() {
        this.testFolder.addFlashcard(this.bonjourFlashcard);
        this.testFolder.addFlashcard(this.savonFlashcard);
        assertEquals(this.savonFlashcard, this.testFolder.getFlashcardByPhrase("savon"));
    }

    @Test
    public void testGetFlashcardByPhraseFlashcardNotExists() {
        assertEquals("", this.testFolder.getFlashcardByPhrase("bonjour").getPhrase());
    }
}