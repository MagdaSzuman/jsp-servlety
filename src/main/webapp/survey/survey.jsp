<%@ page import="com.sda.servlets.survey.Survey" %>
<%@ page import="com.sda.servlets.survey.SurveyService" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.sda.servlets.survey.SurveyQuestion" %><%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2018-11-20
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    SurveyService surveyService = SurveyService.instanceOf();
%>
<%
    String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
    Integer surveyId = Integer.valueOf(StringUtils.substringAfterLast(path, "/"));
    Survey survey = surveyService.findById(surveyId);
%>

<html>

<head>
    <style>

        .survey-container {
            display: flex;
            flex-direction: column;
            /*min-width: 300px;*/
            max-width: 750px;
            background-color: lightgray;
            border: 1px solid black;
            border-radius: 5px;
            padding: 10px;
        }

        .survey-header {
            background-color: gray;
            color: white;
            font-weight: bold;
            padding: 10px;
        }

        .survey-header h1 {
            margin: 0px;
        }

        .survey-header h6 {
            margin-bottom: 0px;
            margin-top: 6px;
        }

        .survey-questions {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .survey-question {
            width: 300px;
            margin: 10px;
            background-color: gray;
        }

        .survey-question ul {
            margin-top: 0px;
        }

        .survey-question-header {
            display: flex;
            justify-content: center;
        }

        .survey-question-header h4 {
            margin: 10px 0px;

            /*margin: 10px 0px 10px;*/
            /*margin: 10px 0px 10px 0px;*/
            /*margin-top: 10px;*/
            /*margin-bottom: 10px;*/
            /*tak samo można sie bawić z paddingiem*/
        }

        .survey-title {
            display: flex;
            justify-content: center;
        }

        #content {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .new-question-form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

    </style>
</head>

<body>
<div id="content">
    <div class="survey-container">
        <div class="survey-header">
            <div class="survey-title">
                <h1><%=survey.getTitle()%>
                </h1>
            </div>
            <h6><%=survey.getDescription()%>
            </h6>
        </div>

        <div class="survey-questions">
            <% for (SurveyQuestion question : survey.getQuestions()) {%>
            <div class="survey-question">
                <div class="survey-question-header">
                    <h4><%=question.getText()%></h4>
                </div>
                <ul>
                    <% for (String answer : question.getAnswers()) { %>
                    <li><%=answer%>
                    </li>
                    <% } %>
                </ul>
            </div>
            <% } %>
        </div>
    </div>

    <div>

        <form class="new-question-form" action="<%= path + "/answers"%>" method="post">
            <div>
                Text: <input type="text" name="text"><br>
            </div>
            <div>
                Answers:<input type="text" name="answer1">,
                <input type="text" name="answer2">,
                <input type="text" name="answer3">,
                <input type="text" name="answer4">
            </div>
            <input type="submit">
        </form>
    </div>

</div>
</body>
</html>


