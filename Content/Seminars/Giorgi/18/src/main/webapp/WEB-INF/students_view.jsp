<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
        <!DOCTYPE html>
        <html>
        <head>
        <title>Students Web App</title>
        <meta charset='utf-8' />
        </head>
        <body>
        <div>
        <c:choose>
            <c:when test="${whoami != null}">
                Hello <%= request.getAttribute("whoami") %>
            </c:when>
            <c:otherwise>
                <form action='/students' method='post'>
                    <input type='text' placeholder='First Name' name='first_name' />
                    <input type='text' placeholder='Last Name' name='last_name' />
                    <input type='text' placeholder='Year' name='enrollment_year' />
                    <input type='submit' value='Add student' />
                </form>
            </c:otherwise>
         </c:choose>
        <form action='/students' method='get'>
            <input type='text' placeholder='First Name' name='first_name' />
            <input type='text' placeholder='Last Name' name='last_name' />
            <input type='text' placeholder='Year' name='enrollment_year' />
            <input type='submit' value='Search' />
        </form>
        <table class='table' id='student - table'>
        <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Year</th>
        </tr>
        <c:forEach var="st" items="${students}">
            <tr>
            <td><c:out value="${st.firstName}" /></td>
            <td><c:out value="${st.lastName}" /></td>
            <td><c:out value="${st.enrollmentYear}" /></td>
            </tr>
        </c:forEach>
        </table>
        </div>
        </body>
        </html>