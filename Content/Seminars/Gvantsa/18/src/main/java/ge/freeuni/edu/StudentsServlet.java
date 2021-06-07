package ge.freeuni.edu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>My Test Page</title>");
        writer.println("<link");
        writer.println("rel=\"icon\" href=\"https://love2dev.com/img/2000px-instagram_logo_2016svg-2000x2000.png\"/>");
        writer.println("<meta charset=\"utf-8\" />");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<div class=\"container\">");
        writer.println("<form id='form' action=\"/student\" method=\"post\">\n");
        writer.println("<input type=\"text\" placeholder=\"First Name\" name=\"first-name\" />");
        writer.println("<input type=\"text\" placeholder=\"Last Name\" name=\"last-name\" />");
        writer.println("<input type=\"text\" placeholder=\"Year\" name=\"year\" />");
        writer.println("<input type=\"submit\" value=\"Add student\" id=\"add-student\" />");
        writer.println("</form>");
        writer.println("<table class=\"table\" id=\"student-table\">");
        writer.println("<tr>");
        writer.println("<th>First name</th>");
        writer.println("<th>Last name</th>");
        writer.println("<th>Year</th>");
        writer.println("</tr>");

        StudentDao students = getStore(request);

        for (Student student: students.getAll()){
            writer.println("<tr>");
            writer.println("<td>" + student.getFirstName() +"</td>");
            writer.println("<td>" + student.getLastName() +"</td>");
            writer.println("<td>" + student.getEnrollmentYear() +"</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student(request.getParameter("first-name"),
                request.getParameter("last-name"),
                Integer.valueOf(request.getParameter("year")));
        getStore(request).add(student);
        response.sendRedirect("/student");
    }

    private StudentDao getStore(HttpServletRequest request){
        StudentDao students = (StudentDao) request.getServletContext().getAttribute("store");

        if (students == null){
            students = new ArrayListStudentDao();
            request.getServletContext().setAttribute("store", students);
        }

        return students;
    }
}
