<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
[
    <c:forEach var="st" items="${students}" varStatus = "status">
    {
        "first_name": "<c:out value='${st.firstName}' />",
        "last_name": "<c:out value='${st.lastName}' />",
        "enrollment_year": <c:out value="${st.enrollmentYear}" />
    }
    <c:if test="${!status.last}">,</c:if>
    </c:forEach>
]