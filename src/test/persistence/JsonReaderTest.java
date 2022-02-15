package persistence;

import model.Flashcard;
import model.Folder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// class inspired by JsonSerializationDemo (link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/invalid.json");
        try {
            Folder folder = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyFolder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFolder.json");
        try {
            Folder folder = reader.read();
            assertEquals(0, folder.getFlashcards().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralFolder() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFolder.json");
        try {
            Folder folder = reader.read();
            assertEquals(3, folder.getFlashcards().size());
            List<Flashcard> flashcards = folder.getFlashcards();
            checkFlashcard("bonjour", "hello", 1, flashcards.get(0));
            checkFlashcard("savon", "soap", 2, flashcards.get(1));
            checkFlashcard("lardon", "bacon", 3, flashcards.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
