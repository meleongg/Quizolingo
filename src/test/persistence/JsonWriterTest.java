package persistence;

import model.Folder;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

// class inspired by JsonSerializationDemo (link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Folder folder = new Folder();
            JsonWriter writer = new JsonWriter("./data/dne:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // works perfectly
        }
    }


}
