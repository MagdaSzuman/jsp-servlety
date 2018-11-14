package com.sda.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LinkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        resp.addHeader("Content-Type", "text/html");

        writer.println("<br/>\n" +
                "\t<a href =\"https://www.google.pl\">Google</a>\n" +
                "<br/>\n" +
                "\t<a href =\"https://www.onet.pl/\">Onet</a>\t\n" +
                "<br/>\n" +
                "\t<a href =\"https://www.wp.pl/\">Wp</a>\t"    );
    }
}
