package testDB;

import com.ak98neon.entity.Student;
import com.ak98neon.util.WorkBase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestWorkBase {
    private static Student student = new Student("test", 20, 1, 20000);

    @Before
    public void createdTestTableDB() {
        WorkBase.createTable();
        WorkBase.insertStudent(student);
    }

    @Test
    public void createTable() {
        assertTrue(WorkBase.createTable());
    }

    @Test
    public void insertStudent() {
        final boolean res = WorkBase.insertStudent(student);
        assertTrue(res);
    }

    @Test
    public void deleteStudent() {
        assertTrue(WorkBase.deleteStudent(student));
    }

    @Test
    public void updateStudent() {
        assertTrue(WorkBase.updateStudent("newTEST", 19, 2, 30000, student.getId()));
    }

    @Test
    public void selectStudent() {
        Student s = WorkBase.selectStudent(student.getName());
        assert s != null;
        assertNotNull(s.getName());
    }

    @Test
    public void selectAllStudent() {
        List<Student> list = WorkBase.selectAllStudent();
        assertTrue(list.size() > 0);
    }

    @Test
    public void dropTable() {
        assertTrue(WorkBase.dropTable());
    }
}
