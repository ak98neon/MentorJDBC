package testView;

import com.ak98neon.model.Student;
import com.ak98neon.view.Main;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMain {
    private static Student student = new Student("test", 20, 1, 20000);

    @Test
    public void testCreateTable() {
        assertTrue(Main.createTable());
    }

    @Test
    public void testInsert() {
        Main.createTable();
        Main.insertStudent(student);
        Student s = Main.selectStudent(student.getName());
        assertEquals(student.getName(), s.getName());
    }

    @Test
    public void testSelect() {
        Student s = Main.selectStudent(student.getName());
        assertNotNull(s.getName());
    }
}
