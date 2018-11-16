package com.sda.servlets.links;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class LinksServlet extends HttpServlet {

    private LinksService linksService;

    @Override
    public void init() throws ServletException {
        this.linksService =LinksService.instanceOf();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        resp.addHeader("Content-Type", "text/html");

        writer.println("<div>");

        String errorMessage = req.getParameter("error_message");
        if (errorMessage != null) {
            writer.println("<p style=\"color:red;\">" + errorMessage + "</p>");
        }

        createCreationForm(writer);
        writer.println("<br>");
        createQueryForm(writer);
        String query = Optional.ofNullable(req.getParameter("q")).orElse("");
        writer.println("Moje linki:");
        List<Link> links = linksService.findByQuery(query);

//        for (int i = 0; i < links.size(); i++) {
//            Link link = links.get(i);
//            writer.println("<a href=\"" + req.getContextPath() + req.getServletPath() + "/" + link.getId() + "\">");
//            writer.println("<p>" + link.getText() +"</p>");
//            writer.println("</a>");
//        }



        for (Link link : linksService.findAll()) {
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

    private void createCreationForm(PrintWriter writer) {
        String form = "<form action=\"\" method=\"post\">\n" +
                "    Link: <input type=\"text\" name=\"link\">\n" +
                "    <br>\n" +
                "    Text: <input type=\"text\" name=\"text\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\"> \n" +
                "</form>";
        writer.println(form);
    }

    private void createQueryForm(PrintWriter writer) {
        String queryForm = "<form action=\"\" method=\"get\">\n" +
                "    Search: <input type=\"text\" name=\"q\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\">\n" +
                "</form>";
        writer.println(queryForm);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String link = req.getParameter("link");
        String text = req.getParameter("text");

        StringBuilder redirectBuilder = new StringBuilder();
        redirectBuilder.append(req.getContextPath()).append(req.getServletPath());

        if (link == null || text == null) {
            redirectBuilder.append("?error_message=Bledne dane");
            // resp.sendRedirect(req.getContextPath() + req.getServletPath() + "?error_message=Bledne dane");
        } else {
            Link linkObject = new Link(link, text);
            linksService.save(linkObject);

            /*
            Link linkObject = new Link(link, text);
            links.add(linkObject);
            */
            // resp.sendRedirect(req.getContextPath() + req.getServletPath());
        }
        resp.sendRedirect(redirectBuilder.toString());
    }
}
