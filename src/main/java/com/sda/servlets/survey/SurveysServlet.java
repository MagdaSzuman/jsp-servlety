package com.sda.servlets.survey;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SurveysServlet extends HttpServlet {
    private SurveyService surveyService;


    @Override
    public void init() throws ServletException {
        this.surveyService = SurveyService.instanceOf();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("survey/surveys.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Survey survey = SurveyFormDto.fromRequest(req).toDomain();
        System.out.println(survey);
        surveyService.save(survey);
        resp.sendRedirect(req.getContextPath() + req.getServletPath());
    }
}
