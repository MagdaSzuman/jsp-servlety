<%@ page import="com.sda.servlets.survey.SurveyService" %>
<%@ page import="com.sda.servlets.survey.Survey" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%!
    SurveyService surveyService = SurveyService.instanceOf();
%>

<ul>
    <%
        List<Survey> surveyList = surveyService.findAll();
        for (Survey survey: surveyList) { %>
        <li>
            <%=
                survey.getTitle()
            %>
        </li>
        <% }
    %>
</ul>

<br>
<form action="" method="post">
    Title: <input type="text" name="title"> <br>
    Description: <textarea name="description" id="" cols="30" rows="10"></textarea>
    <input type="submit" value="Dodaj">
</form>




</body>
</html>
