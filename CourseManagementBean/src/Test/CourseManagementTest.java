package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Entities.GradeLetter;
import SpringBean.CourseManagementBean;
import org.junit.jupiter.api.Test;

public class CourseManagementTest {

    @Test
    public void addStudentFalse() {
        CourseManagementBean courseManager = new CourseManagementBean("Test", "Test");

        boolean resultTF = courseManager.addStudent("TestStudent", null);
        boolean resultTF2 = courseManager.addStudent("TestStudent", "");
        boolean resultFT = courseManager.addStudent(null, "TestMetric");
        boolean resultFT2 = courseManager.addStudent("", "TestMetric");
        boolean resultFF = courseManager.addStudent(null, null);
        boolean resultFF2 = courseManager.addStudent("", "");

        boolean result = !(resultFT || resultFT2 || resultTF || resultTF2 || resultFF || resultFF2);
        assertTrue(result);
    }

    @Test
    public void addStudentTrue() {
        CourseManagementBean courseManager = new CourseManagementBean("Test", "Test");

        courseManager.addStudent("TestStudent", "TestMetric");

        assertTrue(courseManager.hasStudents());

        assertEquals("TestStudent", courseManager.getRegisteredStudents().get(0).getName(), "Name not registered correctly");
        assertEquals("TestMetric", courseManager.getRegisteredStudents().get(0).getMetricNum(), "Metric not registered correctly");
    }

    @Test
    public void editStudentGradeTest() {
        CourseManagementBean courseManager = new CourseManagementBean("Test", "Test");
        courseManager.addStudent("TestStudent", "TestMetric");

        assertTrue(!courseManager.editStudentMarks(0, 100, 100, 100));
        assertTrue(!courseManager.editStudentMarks(0, -1, -1, -1));

        courseManager.setCourseMarks(10, 40, 50);
        assertTrue(!courseManager.editStudentMarks(0, 20, 30, 40));
        assertTrue(!courseManager.editStudentMarks(0, 5, 50, 40));
        assertTrue(!courseManager.editStudentMarks(0, 5, 30, 60));

        assertTrue(courseManager.editStudentMarks(0, 10, 40, 50));

        assertEquals(courseManager.getCourse().getMidTermFUll(), 10, "midterm mark could not be set");
        assertEquals(courseManager.getCourse().getProjectFUll(), 40, "project mark could not be set");
        assertEquals(courseManager.getCourse().getExamFUll(), 50, "exam mark could not be set");
    }

    @Test
    public void calculateGradeTest() {
        CourseManagementBean courseManager = new CourseManagementBean("Test", "Test");
        courseManager.addStudent("TestStudent", "TestMetric");
        courseManager.setCourseMarks(10, 40, 50);

        courseManager.addStudent("TestStudent1", "TestMetric1");
        courseManager.editStudentMarks(0, 10, 40, 50);
        courseManager.calculateStudentGrade(0);
        assertEquals(100, courseManager.getStudentGrade(0).getFinalGrade(), "Full marks Student is not calculated correctly.");
        assertEquals(GradeLetter.A_Plus, courseManager.getStudentGrade(0).getGradeLetter(), "Full marks Student grade is not calculated correctly.");

        courseManager.addStudent("TestStudent2", "TestMetric2");
        courseManager.editStudentMarks(1, 5, 20, 25);
        courseManager.calculateStudentGrade(1);
        assertEquals(50, courseManager.getStudentGrade(1).getFinalGrade(), "50% marks Student is not calculated correctly.");
        assertEquals(GradeLetter.C_Minus, courseManager.getStudentGrade(1).getGradeLetter(), "50% marks Student grade is not calculated correctly.");

    }


}
