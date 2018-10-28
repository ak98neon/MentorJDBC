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
    public void InitAndInsertStudent() {
        WorkBase.createTable();
        WorkBase.insertStudent(student);
    }

    @Test
    public void Create_Query_CreationTable() {
        assertTrue(WorkBase.createTable());
    }

    @Test
    public void Insert_Student_StudentInsertInTable() {
        final boolean res = WorkBase.insertStudent(student);
        assertTrue(res);
    }

    @Test
    public void Delete_Student_StudentDeleted() {
        assertTrue(WorkBase.deleteStudent(student));
    }

    @Test
    public void Update_Student_StudentUpdate() {
        assertTrue(WorkBase.updateStudent("newTEST", 19, 2, 30000, student.getId()));
    }

    @Test
    public void Select_StudentName_StudentSelectedForName() {
        Student s = WorkBase.selectStudent(student.getName());
        assert s != null;
        assertNotNull(s.getName());
    }

    @Test
    public void Select_Query_StudentAllSelected() {
        List<Student> list = WorkBase.selectAllStudent();
        assertTrue(list.size() > 0);
    }

    @Test
    public void Drop_Query_TableIsDrop() {
        assertTrue(WorkBase.dropTable());
    }
}
