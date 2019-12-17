package SpringBean;

import Entities.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementBean {

    ApplicationContext context;
    ArrayList<CourseManagementBean> courseManagementBeans;

    StudentManagementBean() {
        context = new ClassPathXmlApplicationContext("CourseManagerBeans.xml");
        courseManagementBeans = new ArrayList<>();
    }

    public void manageCourse(int selectedCourse) {
        if (selectedCourse >= courseManagementBeans.size() || selectedCourse < 0) {
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

            try {
                switch (input.nextInt()) {
                    case 1:
                        System.out.println("Registered students:");
                        if (courseManager.hasStudents()) {
                            System.out.println(courseManager.getRegisteredStudents().toString());
                        } else System.out.println("No available students registered for this course.");
                        break;

                    case 2:
                        System.out.println("Enter the student's name / metric number sequentially:");

                        if (courseManager.addStudent(input.next(), input.next()))
                            System.out.println("Student registered successfully!.");
                        else
                            System.out.println("Error entering parameters.");

                        break;

                    case 3:
                        System.out.println("Enter the student's index:");
                        int index = input.nextInt() - 1;
                        if (courseManager.hasStudentIndex(index)) {
                            System.out.println("Enter student's Midterm / Project / Final Exam marks sequentially:");

                            if (courseManager.editStudentGrade(index, input.nextFloat(), input.nextFloat(), input.nextFloat()))
                                System.out.println("Student's record edited successfully!");
                            else
                                System.out.println("Error editing record!. Check entered parameters");
                        } else
                            System.out.println("Student index out of range!.");
                        break;

                    case 4:
                        System.out.println("Enter the student's index:");
                        index = input.nextInt() - 1;
                        if (!courseManager.calculateStudentGrade(index)) {
                            System.out.println("Student's grades:\n" + courseManager.getStudentGrade(index).toString());
                        } else System.out.println("Student index invalid!.");
                        break;

                    case 5:
                        returnCondition = true;
                        break;

                    default:
                        break;
                }

            } catch (InputMismatchException e) {
                e.fillInStackTrace();
                System.out.println("Please enter a valid input.");
                input = new Scanner(System.in);
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
        if (courseName.isBlank() || courseCode.isBlank()) return false;

        courseManagementBeans.add((CourseManagementBean) context.getBean("courseManagementBean", courseName, courseCode));
        return true;
    }
}
