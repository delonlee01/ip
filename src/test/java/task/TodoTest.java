package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toDataString_doneStatus_success() {
        Todo task = new Todo("test task");
        task.markAsDone();
        assertEquals("1|todo test task| ", task.toDataString());
    }

    @Test
    public void toDataString_notDoneStatus_success() {
        Todo task = new Todo("test task");
        assertEquals("0|todo test task| ", task.toDataString());
    }

    @Test
    public void toDataString_hasTags_success() {
        Todo task = new Todo("test task");
        task.addTag(new Tag("testing"));
        assertEquals("0|todo test task|testing", task.toDataString());
    }

    @Test
    public void toString_doneStatus_success() {
        Todo task = new Todo("test task");
        task.markAsDone();
        assertEquals("[T][X] test task", task.toString());
    }

    @Test
    public void toString_notDoneStatus_success() {
        Todo task = new Todo("test task");
        assertEquals("[T][ ] test task", task.toString());
    }
}
