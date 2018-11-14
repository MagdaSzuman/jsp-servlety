package com.sda.servlets;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "text/html");

        String message = req.getParameter("message");

        PrintWriter writer = resp.getWriter();

        createMessage(message, writer);
        createForm(writer);

        if (req.getParameter("a")!= null && req.getParameter("b")!= null && req.getParameter("operation")!= null ) {
            handleCalculation(req, resp, writer);
        }
    }

    private void handleCalculation(HttpServletRequest req, HttpServletResponse resp, PrintWriter writer) throws IOException {
        Integer a = mapToInt(req.getParameter("a"));
        Integer b = mapToInt(req.getParameter("b"));
        String operation = req.getParameter("operation");
        writer.print("Result: ");
        if ("+".equals(operation)) {
            writer.println(a + b);
        } else if ("-".equals(operation)) {
            writer.println(a - b);
        } else if ("*".equals(operation)) {
            writer.println(a * b);
        } else if ("/".equals(operation)) {
            if (b == 0) {
                // resp.setStatus(302);
                // resp.addHeader("Location", "https://pl.wikipedia.org/wiki/Dzielenie");
                resp.sendRedirect(req.getContextPath()+req.getServletPath()  + "?message=Nie dziel przez zero");
            } else {
                writer.println((double) a / b);
            }
        }
    }

    private void createMessage(String message, PrintWriter writer) {
        if (message != null) {
            writer.println("<p style=\"color:red;\">" + message + "</p>");
        }
    }

    private void createForm(PrintWriter writer) {
        writer.println("<form action=\"\">");
        writer.println("<input type=\"number\" name=\"a\">");
        writer.println("<select name=\"operation\">");
        writer.println("<option value=\"+\">Dodawanie</option>");
        writer.println("<option value=\"-\">Odejmowanie</option>");
        writer.println("<option value=\"*\">Mnozenie</option>");
        writer.println("<option value=\"/\">Dzielenie</option>");
        writer.println("</select>");
        writer.println("<input type=\"number\" name=\"b\">");
        writer.println("<input type=\"submit\" value=\"=\">");
        writer.println("</form>");
    }

    private Integer mapToInt(String value) {
        if (value == null || !StringUtils.isNumeric(value)) {
            return 0;
        }
        return Integer.valueOf(value);
    }
}