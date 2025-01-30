package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static final LocalDate FROM_DATE = LocalDate.parse("01/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private static final LocalDate TO_DATE = LocalDate.parse("02/01/2025",
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Test
    public void getFrom_success() {
        Event task = new Event("test task", FROM_DATE, TO_DATE);
        assertEquals(LocalDate.parse("01/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")), task.getFrom());
    }

    @Test
    public void getTo_success() {
        Event task = new Event("test task", FROM_DATE, TO_DATE);
        assertEquals(LocalDate.parse("02/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")), task.getTo());
    }

    @Test
    public void toDataString_doneStatus_success() {
        Event task = new Event("test task", FROM_DATE, TO_DATE);
        task.markAsDone();
        assertEquals("1|event test task /from 01/01/2025 /to 02/01/2025", task.toDataString());
    }

    @Test
    public void toDataString_notDoneStatus_success() {
        Event task = new Event("test task", FROM_DATE, TO_DATE);
        assertEquals("0|event test task /from 01/01/2025 /to 02/01/2025", task.toDataString());
    }

    @Test
    public void toString_doneStatus_success() {
        Event task = new Event("test task", FROM_DATE, TO_DATE);
        task.markAsDone();
        assertEquals("[E][X] test task (from: 01 Jan 2025 to: 02 Jan 2025)", task.toString());
    }

    @Test
    public void toString_notDoneStatus_success() {
        Event task = new Event("test task", FROM_DATE, TO_DATE);
        assertEquals("[E][ ] test task (from: 01 Jan 2025 to: 02 Jan 2025)", task.toString());
    }
}
