package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TagTest {
    @Test
    public void toString_success() {
        Tag tag = new Tag("testing");
        assertEquals("#testing", tag.toString());
    }
}
