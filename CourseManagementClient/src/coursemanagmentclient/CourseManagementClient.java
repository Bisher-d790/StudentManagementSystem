package coursemanagmentclient;

import coursemanagment.spring.CourseManagementBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*************************************************************************************************
 *
 * COMPANION CODE FOR THE BOOK ?Component Oriented Development & Assembly ? CODA Using Java?
 *
 * @author ? Piram Manickam, Sangeetha S, Subrahmanya S V
 * @see -  http://www.codabook.com
 *
 * <br><br><b>CODE CONTRIBUTORS</b> ? <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
 * Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>
 **************************************************************************************************/

public class CourseManagementClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        CourseManagementBean courseManagementBean = (CourseManagementBean) context.getBean("courseManagementBean");

        float grade = courseManagementBean.getStudentGrades(2);

        System.out.println(grade);

    }

}
