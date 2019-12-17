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

    public StudentManagementBean() {
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
                    "\n3- Edit student's marks" +
                    "\n4- Calculate a student's grade." +
                    "\n5- Set course's total marks." +
                    "\n6- Return to main menu.\n");

            try {
                switch (input.nextInt()) {
                    case 1:
                        System.out.println("Registered students:");
                        if (courseManager.hasStudents()) {
                            var students = courseManager.getRegisteredStudents();
                            for (int i = 0; i < students.size(); i++) {
                                System.out.println((i + 1) + "-\t" + students.get(i));
                            }
                            System.out.println();
                        } else System.out.println("No available students registered for this course.");
                        break;

                    case 2:
                        System.out.println("Enter the student's name / metric number sequentially:");

                        if (courseManager.addStudent(input.next(), input.next()))
                            System.out.println("Student registered successfully!.");
                        else
                            System.out.println("Error entering parameters.");

                        System.out.println();
                        break;

                    case 3:
                        System.out.println("Enter the student's index:");
                        int index = input.nextInt() - 1;
                        if (courseManager.areCourseMarksSet()) {
                            if (courseManager.hasStudentIndex(index)) {
                                System.out.println("Enter student's Midterm (" + courseManager.getCourse().getMidTermFUll() + "%) /" +
                                        " \nProject (" + courseManager.getCourse().getProjectFUll() + ") /" +
                                        " \nFinal Exam marks (" + courseManager.getCourse().getExamFUll() + ") sequentially:");

                                if (courseManager.editStudentMarks(index, input.nextFloat(), input.nextFloat(), input.nextFloat()))
                                    System.out.println("Student's record edited successfully!");
                                else
                                    System.out.println("Error editing record!. Check entered parameters");
                            } else
                                System.out.println("Student index out of range!.");
                        } else
                            System.out.println("Course total marks are not set!. ");

                        System.out.println();
                        break;

                    case 4:
                        System.out.println("Enter the student's index:");
                        index = input.nextInt() - 1;
                        if (courseManager.hasStudentIndex(index)) {
                            if (courseManager.calculateStudentGrade(index))
                                System.out.println("Student's grades:\n" + courseManager.getStudentGrade(index).toString());
                            else
                                System.out.println("Course grades are invalid.");
                        } else System.out.println("Student index invalid!.");

                        System.out.println();
                        break;

                    case 5:
                        System.out.println("Enter course's Midterm / Project / Final Exam total marks sequentially:" +
                                "\n(Course total marks should sum to 100%)");
                        if (courseManager.setCourseMarks(input.nextInt(), input.nextInt(), input.nextInt()))
                            System.out.println("Course total marks set successfully!");
                        else
                            System.out.println("Course total marks should sum to 100.");

                        System.out.println();
                        break;

                    case 6:
                        returnCondition = true;

                        System.out.println();
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
        if (courseName == null || courseCode == null || courseName.isBlank() || courseCode.isBlank()) return false;

        return courseManagementBeans.add((CourseManagementBean) context.getBean("courseManagementBean", courseName, courseCode));
    }
}
