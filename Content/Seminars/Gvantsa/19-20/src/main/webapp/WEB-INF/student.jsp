<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>

<%@ page import="ge.freeuni.edu.Student" %>
<%@ page import="ge.freeuni.edu.StudentDao" %>
<%@ page import="ge.freeuni.edu.ArrayListStudentDao" %>
<!DOCTYPE html>
<html>
  <head>
    <title>My Test Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <meta charset="utf-8" />
  </head>
  <body>
    <div class="container">
      <form id="form" action="/student" method="post">
        <input type="text" placeholder="First Name" name="first-name" />
        <input type="text" placeholder="Last Name" name="last-name" />
        <input type="text" placeholder="Year" name="year" />
        <input type="submit" value="Add student" id="add-student" />
      </form>
      <table class="table" id="student-table">
        <tr>
          <th>First name</th>
          <th>Last name</th>
          <th>Year</th>
        </tr>
      <%  StudentDao students = (StudentDao)(application.getAttribute("store"));

        for (Student student: students.getAll()){      %>

          <tr>
            <td> <%= student.getFirstName() %> </td>
            <td><%= student.getLastName() %></td>
            <td><%= student.getEnrollmentYear() %></td>
          </tr>
        <% } %>
      </table>
    </div>
  </body>
</html>