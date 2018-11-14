package com.sda.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LinkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        List <Link> links = new ArrayList<>();
        links.add(new Link("https://www.google.pl","Google"));
        links.add(new Link("https://www.onet.pl","Onet"));
        links.add(new Link("https://www.wp.pl","Wp"));

        resp.addHeader("Content-Type", "text/html");
        writer.println("<div>");
        writer.println("Moje linki:");
        for (Link link: links) {
            writer.println("<br/>");
            writer.println("<a href=\"" + link.getUrl() + "\">" + link.getText() + "</a>");
        }
        writer.println("</div>");
/*
        writer.println("<br/>");
        writer.println("<a href=\"http://www.google.com\">Google</a>");
        writer.println("<br/>");
        writer.println("<a href=\"http://pl.wikipedia.com\">Wikipedia</a>");
        writer.println("<br/>");
        writer.println("<a href=\"http://onet.pl\">Onet</a>");

        */
    }
}
