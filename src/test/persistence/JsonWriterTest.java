package persistence;

import model.Flashcard;
import model.Folder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// SOURCE https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Folder folder = new Folder();
            JsonWriter writer = new JsonWriter("./data/\0®fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // works perfectly
        }
    }

    @Test
    public void testWriterEmptyFolder() {
        try {
            Folder folder = new Folder();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFolder.json");
            writer.open();
            writer.write(folder);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFolder.json");
            folder = reader.read();
            assertEquals(0, folder.getFlashcards().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralFolder() {
        try {
            Folder folder = new Folder();
            folder.addFlashcard(new Flashcard("bonjour", "hello", 3));
            folder.addFlashcard(new Flashcard("lardon", "bacon", 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFolder.json");
            writer.open();
            writer.write(folder);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFolder.json");
            folder = reader.read();
            assertEquals(2, folder.getFlashcards().size());
            List<Flashcard> flashcards = folder.getFlashcards();
            checkFlashcard("bonjour", "hello", 3, flashcards.get(0));
            checkFlashcard("lardon", "bacon", 2, flashcards.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
