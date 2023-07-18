import com.mmaksin.java.DatabaseManager;
import com.mmaksin.java.beans.User;
import com.mmaksin.java.commands.Add;
import com.mmaksin.java.commands.Delete;
import com.mmaksin.java.commands.PrintAll;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintAllCommandTest {

    private static DatabaseManager databaseManager;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    static void setup() {
        System.setOut(new PrintStream(outContent));
        databaseManager = new DatabaseManager();
    }

    @Test
    public void printAllOutput() {
        User user = new User(1, "ad1", "Robert");
        Add addCommand = new Add(databaseManager, user);
        addCommand.execute();

        outContent.reset();
        PrintAll printAllCommand = new PrintAll(databaseManager);
        printAllCommand.execute();
        assertEquals("User {id=1, guid='ad1', username='Robert'}\r\n", outContent.toString());
    }

    @AfterAll
    static void reset() {
        System.setOut(originalOut);
        Delete deleteCommand = new Delete(databaseManager);
        deleteCommand.execute();
    }
}
