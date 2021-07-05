import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseTest {
    @Mock
    private StudentDao dao;

    @InjectMocks
    private Course c;

    @Test
    public void courseAlreadyFull() {
        System.out.println("-------------");
        System.out.println(c.getMaxAmount());
        final int numStudents = 100;
        when(dao.getAll()).thenAnswer(invocation -> {
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < numStudents; i++) {
                students.add(null);
            }
            return students;
        }).thenAnswer(invocation -> {
            List<Student> students = new ArrayList<>();
            for (int i = 1; i < numStudents; i++) {
                students.add(null);
            }
            return students;
        });
        // Course c = new Course("OOP", numStudents, dao);
        c.setMaxAmount(numStudents);
        assertFalse(c.addStudent(null));
        assertTrue(c.addStudent(null));
    }

    @Test
    public void throwsExceptionOnEmptyDao() {
        when(dao.getAll()).thenReturn(List.of());
        // Course c = new Course("OOP", 100, dao);
        Throwable t = assertThrows(IllegalStateException.class, () -> c.getStudentWithHighestGpa());
        assertEquals(Course.EMPTY_COURSE_MESSAGE, t.getMessage());
    }

    @Test
    public void main() {
        Main.main(new String[]{"1", "2"});
    }
}
