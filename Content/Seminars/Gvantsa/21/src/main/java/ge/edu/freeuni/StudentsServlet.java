package ge.edu.freeuni;

import com.sun.net.httpserver.HttpContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


public class StudentsServlet extends HttpServlet {
    private final String WHOAMI = "whoami";
//    private StudentDao students;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        students = new ArrayListStudentDao();
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String me = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(WHOAMI)) {
                    me = c.getValue();
                }
            }
        }
        if (me != null) {
            req.setAttribute(WHOAMI, me);
        }
        Filter f = buildFilter(req);
        StudentDao students = getStore(req);
        req.setAttribute("students", students.filter(f));
        req.getRequestDispatcher("WEB-INF/students_view.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDao students = getStore(req);
        Student st = new Student(
                req.getParameter("first_name"),
                req.getParameter("last_name"),
                Integer.valueOf(req.getParameter("enrollment_year")));
        students.add(st);
        resp.addCookie(new Cookie(WHOAMI, st.getFirstName()));
        resp.sendRedirect("/students");
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
