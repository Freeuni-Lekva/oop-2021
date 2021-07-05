package ge.edu.freeuni.api;

import ge.edu.freeuni.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


public class StudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Filter f = buildFilter(req);
        StudentDao students = getStore(req);
        req.setAttribute("students", students.filter(f));
        req.getRequestDispatcher("/WEB-INF/api/students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDao students = getStore(req);
        Student st = new Student(
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                Integer.valueOf(req.getParameter("enrollment_year")));
        students.add(st);
    }

    private StudentDao getStore(HttpServletRequest req) {
        ServletContext ctx = req.getServletContext();
        StudentDao students = (StudentDao) ctx.getAttribute("store");
        return students;
    }

    private Filter buildFilter(HttpServletRequest req) {
        Enumeration<String> params = req.getParameterNames();
        AndFilter f = new AndFilter();
        while (params.hasMoreElements()) {
            String p = params.nextElement();
            String v = req.getParameter(p);
            if (v.length() > 0) {
                switch (p) {
                    case "first_name":
                        f.add(new FirstNameFilter(v));
                        break;
                    case "last_name":
                        f.add(new LastNameFilter(v));
                        break;
                    case "enrollment_year":
                        f.add(new EnrollmentYearFilter(Integer.valueOf(v)));
                        break;
                    default:
                        throw new IllegalArgumentException(p);
                }
            }
        }
        if (f.size() > 0) {
            return f;
        } else {
            return new AllFilter();
        }
    }
}
