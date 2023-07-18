import com.mmaksin.java.DatabaseManager;
import com.mmaksin.java.beans.User;
import com.mmaksin.java.commands.Add;
import com.mmaksin.java.commands.Delete;
import com.mmaksin.java.commands.PrintAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddCommandTest {

    private static DatabaseManager databaseManager;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    static void setup() {
        System.setOut(new PrintStream(outContent));
        databaseManager = new DatabaseManager();

        User user = new User(1, "ad1", "Robert");
        Add addCommand = new Add(databaseManager, user);
        addCommand.execute();
    }

    @Test
    public void addUser2Successfully() {
        User user = new User(2, "ad2", "Peter");
        Add addCommand = new Add(databaseManager, user);
        addCommand.execute();

        outContent.reset();
        PrintAll printAllCommand = new PrintAll(databaseManager);
        printAllCommand.execute();
        assertTrue(outContent.toString().endsWith("User {id=2, guid='ad2', username='Peter'}\r\n") );
    }

    @AfterAll
    static void reset() {
        System.setOut(originalOut);
        Delete deleteCommand = new Delete(databaseManager);
        deleteCommand.execute();
    }
}
