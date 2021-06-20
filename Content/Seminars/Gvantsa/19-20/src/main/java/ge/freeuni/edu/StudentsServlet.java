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
        request.getRequestDispatcher("WEB-INF/student.jsp").forward(request, response);
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

        return students;
    }
}
