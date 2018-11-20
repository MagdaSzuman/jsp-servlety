<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Hello World</h1>

${message}<br/>
<c:out value="${message}"/><br/>
<c:forEach items="${names}" var="name">
    ${name}<br/>
</c:forEach>

<a href="${url}">
    ${text}
</a>

<c:if test="${age gt 18}">
    <h4>Jestes pelnoletni</h4>
</c:if>
<c:if test="${age lt 18}">
    <h4>Nie jestes pelnoletni</h4>
</c:if>

<c:if test="${age2 gt 18}">
    <h4>Jestes pelnoletni</h4>
</c:if>
<c:if test="${age2 lt 18}">
    <h4>Nie jestes pelnoletni</h4>
</c:if>
