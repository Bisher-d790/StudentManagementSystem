package Test;

import SpringBean.CourseManagementBean;
import SpringBean.StudentManagementBean;
import org.junit.jupiter.api.Test;
import org.testng.annotations.AfterTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentManagementTest {

    @Test
    public void addCourseFalse() {
        StudentManagementBean studentManager = new StudentManagementBean();

        boolean resultTF = studentManager.addCourse("TestStudent", null);
        boolean resultTF2 = studentManager.addCourse("TestStudent", "");
        boolean resultFT = studentManager.addCourse(null, "TestMetric");
        boolean resultFT2 = studentManager.addCourse("", "TestMetric");
        boolean resultFF = studentManager.addCourse(null, null);
        boolean resultFF2 = studentManager.addCourse("", "");

        boolean result = !(resultFT || resultFT2 || resultTF || resultTF2 || resultFF || resultFF2);
        assertTrue(result);
    }

    @Test
    public void addStudentTrue() {
        StudentManagementBean studentManager = new StudentManagementBean();

        studentManager.addCourse("TestStudent", "TestMetric");

        assertTrue(studentManager.hasCourses());

        assertEquals("TestStudent", studentManager.getCourses().get(0).getName(), "Name not registered correctly");
        assertEquals("TestMetric", studentManager.getCourses().get(0).getCode(), "Code not registered correctly");
    }
}
