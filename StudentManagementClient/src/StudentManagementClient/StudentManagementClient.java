package StudentManagementClient;

import SpringBean.StudentManagementBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class StudentManagementClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("StudentManagerBean.xml");

        StudentManagementBean studentManagementBean = (StudentManagementBean) context.getBean("studentManagementBean", context);

        Scanner input = new Scanner(System.in);

        System.out.println("Student management system:");

        boolean exitCondition = false;

        do {
            System.out.println("1- Choose course." +
                    "\n2- Add course." +
                    "\n3- Quit.\n");

            switch (input.nextInt()) {
                case 1:
                    System.out.println("Available courses:");

                    if (studentManagementBean.hasCourses()) {
                        System.out.println(studentManagementBean.getCourses().toString());

                        System.out.println("Select a course:\t");
                        studentManagementBean.manageCourse(input.nextInt());
                    } else {
                        System.out.println("No available courses registered.");
                    }
                    break;

                case 2:
                    System.out.println("Enter course name:");
                    String courseName = input.nextLine();

                    System.out.println("Enter course code:");
                    String courseCode = input.nextLine();

                    studentManagementBean.addCourse(courseName, courseCode);
                    break;

                case 3:
                    exitCondition = true;
                    break;

                default:
                    break;
            }

        }
        while (!exitCondition);
    }
}
