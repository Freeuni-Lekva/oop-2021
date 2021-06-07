package ge.edu.freeuni;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDao students = getStore(req);
        resp.setContentType("text/html");
        PrintWriter w = resp.getWriter();
        w.println("<!DOCTYPE html>");
        w.println("<html>");
        w.println("<head>");
        w.println("<title>Students Web App</title>");
        w.println("<meta charset='utf-8' />");
        w.println("</head>");
        w.println("<body>");
        w.println("<div>");
        // Student me = (Student) req.getSession().getAttribute("whoami");
        String id = null;
        for (Cookie c : req.getCookies()) {
            if (c.getName() == "whoami-id") {
                // ხზ რა ხდება :/
                id = c.getValue();
                break;
            }
        }
        Student me = null;
        if (id != null) {
            me = students.getAll().get(Integer.valueOf(id)); // ეს thread-safe არ არის, ვინმე შეიძლება ცვლიდეს იგივე მასივს პარალელურად
        }
        if (me == null) {
            w.println("<form action='/students' method='post'>");
            w.println("<input type='text' placeholder='First Name' name='first_name' />");
            w.println("<input type='text' placeholder='Last Name' name='last_name' />");
            w.println("<input type='text' placeholder='Year' name='enrollment_year' />");
            w.println("<input type='submit' value='Add student' id='add - student' />");
            w.println("</form>");
        } else {
            w.println("<p>Hello " + me.getFirstName() + "!</p>");
        }
        w.println("<table class='table' id='student - table'>");
        w.println("<tr>");
        w.println("<th>First name</th>");
        w.println("<th>Last name</th>");
        w.println("<th>Year</th>");
        w.println("</tr>");
        for (Student st : students.getAll()) {
            w.println("<tr>");
            w.print("<td>" + st.getFirstName() + "</td>");
            w.print("<td>" + st.getLastName() + "</td>");
            w.print("<td>" + st.getEnrollmentYear() + "</td>");
            w.println("</tr>");
        }
        w.println("</table>");
        w.println("</div>");
        w.println("</body>");
        w.println("</html>");
        w.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDao students = getStore(req);
        resp.getWriter().println(req.getParameter("enrollment_year"));
        Student st = new Student(
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                Integer.valueOf(req.getParameter("enrollment_year")));
        students.add(st);
        // req.getSession().setAttribute("whoami", st);
        resp.addCookie(new Cookie("whoami-id", String.valueOf(st.getId())));
        resp.sendRedirect("/students");
    }

    private StudentDao getStore(HttpServletRequest req) {
        StudentDao students = (StudentDao) req.getServletContext().getAttribute("store");
        if (students == null) {
            students = new ArrayListStudentDao();
            req.getServletContext().setAttribute("store", students);
        }
        return students;
    }
}
