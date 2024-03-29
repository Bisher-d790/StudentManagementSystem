package StudentManagementClient;

import SpringBean.StudentManagementBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.InputMismatchException;
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
            try {
                switch (input.nextInt()) {
                    case 1:
                        System.out.println("Available courses:");

                        if (studentManagementBean.hasCourses()) {
                            var courses = studentManagementBean.getCourses();
                            for (int i = 0; i < courses.size(); i++) {
                                System.out.println((i + 1) + "-\t" + courses.get(i));
                            }
                            System.out.println();

                            System.out.println("Select a course number:\t");
                            int index = input.nextInt() - 1;
                            if (index >= 0 && index < studentManagementBean.getCourses().size())
                                studentManagementBean.manageCourse(index);
                            else
                                System.out.println("Course index invalid!.");
                        } else {
                            System.out.println("No available courses registered.");
                        }

                        System.out.println();
                        break;

                    case 2:
                        System.out.println("Enter course name - code sequentially:");

                        if (studentManagementBean.addCourse(input.next(), input.next()))
                            System.out.println("Course registered successfully!");
                        else
                            System.out.println("Error entering parameters!.");

                        System.out.println();
                        break;

                    case 3:
                        exitCondition = true;
                        break;

                    default:
                        break;
                }

            } catch (InputMismatchException e) {
                e.fillInStackTrace();
                System.out.println("Please enter a valid input.");
                input = new Scanner(System.in);
            }
        }
        while (!exitCondition);
    }
}
