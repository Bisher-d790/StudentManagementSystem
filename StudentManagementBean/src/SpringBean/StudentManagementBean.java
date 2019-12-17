package SpringBean;

import Entities.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementBean {

    ApplicationContext context;
    ArrayList<CourseManagementBean> courseManagementBeans;

    StudentManagementBean() {
        context = new ClassPathXmlApplicationContext("CourseManagerBeans.xml");
        courseManagementBeans = new ArrayList<>();
    }

    public void manageCourse(int selectedCourse) {
        if (courseManagementBeans.size() >= selectedCourse) {
            System.out.println("Index out of range!");
        }
        CourseManagementBean courseManager = courseManagementBeans.get(selectedCourse);
        System.out.println("Course " + courseManager.getCourse().toString() + " selected:");

        Scanner input = new Scanner(System.in);
        boolean returnCondition = false;

        do {
            System.out.println("1- Show registered students." +
                    "\n2- Add student." +
                    "\n3- Edit student's grades" +
                    "\n4- Calculate a student's grade." +
                    "\n5- Return to main menu.\n");

            switch (input.nextInt()) {
                case 1:
                    System.out.println("Registered students:");
                    System.out.println(courseManager.getRegisteredStudents().toString());
                    break;

                case 2:
                    System.out.println("Enter the student's name:");
                    String studentName = input.nextLine();

                    System.out.println("Enter the student's metric:");
                    String studentMetric = input.nextLine();

                    courseManager.addStudent(studentName, studentMetric);
                    break;

                case 3:
                    System.out.println("Enter the student's index:");
                    int index = input.nextInt();
                    System.out.println("Enter student's Midterm / Project / Final Exam marks sequentially:");
                    if (courseManager.editStudentGrade(index, input.nextFloat(), input.nextFloat(), input.nextFloat()))
                        System.out.println("Student's record edited successfully!");
                    else
                        System.out.println("Error editing record!. Check entered parameters");
                    break;

                case 4:
                    System.out.println("Enter the student's index:");
                    index = input.nextInt();
                    courseManager.calculateStudentGrade(index);
                    System.out.println("Student's grades:\n" + courseManager.getStudentGrade(index).toString());
                    break;

                case 5:
                    returnCondition = true;
                    break;

                default:
                    break;
            }
        } while (!returnCondition);
    }

    public ArrayList<Course> getCourses() {

        ArrayList<Course> courses = new ArrayList<>();
        courseManagementBeans.forEach(course -> {
            courses.add(course.getCourse());
        });

        return courses;
    }

    public boolean hasCourses() {
        return !courseManagementBeans.isEmpty();
    }

    public boolean addCourse(String courseName, String courseCode) {
        if (courseName.isBlank() && courseCode.isBlank()) return false;

        courseManagementBeans.add((CourseManagementBean) context.getBean("courseManagementBean", courseName, courseCode));
        return true;
    }
}
