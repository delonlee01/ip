package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static final LocalDate BY_DATE = LocalDate.parse("01/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Test
    public void getBy_success() {
        Deadline task = new Deadline("test task", BY_DATE);
        assertEquals(LocalDate.parse("01/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")), task.getBy());
    }

    @Test
    public void toDataString_doneStatus_success() {
        Deadline task = new Deadline("test task", BY_DATE);
        task.markAsDone();
        assertEquals("1|deadline test task /by 01/01/2025| ", task.toDataString());
    }

    @Test
    public void toDataString_notDoneStatus_success() {
        Deadline task = new Deadline("test task", BY_DATE);
        assertEquals("0|deadline test task /by 01/01/2025| ", task.toDataString());
    }

    @Test
    public void toDataString_hasTags_success() {
        Deadline task = new Deadline("test task", BY_DATE);
        task.addTag(new Tag("testing"));
        assertEquals("0|deadline test task /by 01/01/2025|testing", task.toDataString());
    }

    @Test
    public void toString_doneStatus_success() {
        Deadline task = new Deadline("test task", BY_DATE);
        task.markAsDone();
        assertEquals("[D][X] test task (by: 01 Jan 2025)", task.toString());
    }

    @Test
    public void toString_notDoneStatus_success() {
        Deadline task = new Deadline("test task", BY_DATE);
        assertEquals("[D][ ] test task (by: 01 Jan 2025)", task.toString());
    }
}
