<%@ page import="com.sda.servlets.survey.SurveyService" %>
<%@ page import="com.sda.servlets.survey.Survey" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .form{
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .new-question-button{
            padding: 2px;
            background-color: gray;
            border: 1px solid black;
            border-radius: 1px;
        }

    </style>
</head>
<body>
<%!
    SurveyService surveyService = SurveyService.instanceOf();
%>

<ul>
    <%
        List<Survey> surveyList = surveyService.findAll();
        String pathToSurveys = request.getAttribute("javax.servlet.forward.request_uri")+"/";
        for (Survey survey: surveyList) { %>
        <li>
            <a href="<%= pathToSurveys+survey.getId() %>">
                <%=
                    survey.getTitle()
                %>
            </a>
        </li>
        <% }
    %>
</ul>

<br>
<form action="" method="post" class="form">
    <div>Title: <input type="text" name="title"> <br></div>
    <div>Description: <textarea name="description" id="" cols="30" rows="10"></textarea><br></div>

    <div class="form" id="questions">

    </div>

    <p class="new-question-button" onclick="addQuestion()">Add question</p>
    <input type="submit" value="Dodaj">
</form>


<script>
    var questionId = 1;
    // function removeQuestion(e) {
    //     e.element
    // }
    function addQuestion() {
        var question = document.createElement('div');
        question.className='form';
        question.innerHTML= `
        <h2>Question\${questionId}:</h2>
            <div>
                Text: <input type="text" name="question\${questionId}text"><br>
            </div>
            <div>
                Answers:<input type="text" name="question\${questionId}answer1">,
                <input type="text" name="question\${questionId}answer2">,
                <input type="text" name="question\${questionId}answer3">,
                <input type="text" name="question\${questionId}answer4">
            </div>
        `;
        questionId++;
        document.getElementById('questions').appendChild(question);
    }
    addQuestion();
</script>

</body>
</html>
