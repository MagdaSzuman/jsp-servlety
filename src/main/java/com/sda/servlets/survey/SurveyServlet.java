package com.sda.servlets.survey;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SurveyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/survey/survey.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String substring = pathInfo.substring(1);
        Integer surveyId = Integer.valueOf(StringUtils.substringBefore(substring, "/"));
        String option = StringUtils.substringAfter(substring, "/");

        if ("answers".equals(option)){
            String text = req.getParameter("text");
            List<String> answers = Arrays.asList(
                    req.getParameter("answer1"),
                    req.getParameter("answer2"),
                    req.getParameter("answer3"),
                    req.getParameter("answer4"));
            SurveyQuestion surveyQuestion = new SurveyQuestion(text, answers);
            try {
                surveyService.findById(surveyId).getQuestions().add(surveyQuestion);
            } catch (SurveyDoesNotExistsException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath()+req.getServletPath()+"/"+surveyId);
    }

    private SurveyService surveyService;

    @Override
    public void init() throws ServletException {
        this.surveyService = SurveyService.instanceOf();
    }
}
