package com.sda.servlets.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        this.usersService = UsersService.instanceOf();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Integer age = Integer.valueOf(req.getParameter("age"));
        String gender = req.getParameter("gender");

        StringBuilder redirectBuilder = new StringBuilder();
        redirectBuilder.append(req.getContextPath()).append(req.getServletPath());

        if (firstName == null || lastName == null || age == null || gender == null) {
            redirectBuilder.append("?error_message=Bledne dane");

        } else {
            User user = new User(firstName, lastName, age, gender);
            usersService.save(user);
        }
        resp.sendRedirect(redirectBuilder.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        resp.addHeader("Content-Type", "text/html");

        String errorMessage = req.getParameter("error_message");
        if (errorMessage != null) {
            writer.println("<p style=\"color:red;\">" + errorMessage + "</p>");
        }

        createCreationForm(writer);
        writer.println("<br>");
        createQueryForm(writer);
        String query = Optional.ofNullable(req.getParameter("q")).orElse("");
        writer.println("Nasi uzytkownicy");
        List<User> users = usersService.findByQuery(query);

        /*
        for (User user:users){
            writer.println("<br/>");
            writer.println("<p>" + user.getFirstName() + " " + user.getLastName() + "(" + user.getGender() + ") Age: " + user.getAge() + "</p>");
        }
        */
        /*
        users.forEach(user->
                writer.println("<p>" + user.getFirstName() + " " + user.getLastName() + "(" + user.getGender() + ") Age: " + user.getAge() + "</p>"));
        */
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            writer.println("<a href=\"" + req.getContextPath() + req.getServletPath() + "/" + user.getId() + "\">");
            writer.println("<p>" + user.getFirstName() + " " + user.getLastName() +"</p>");
            writer.println("</a>");
        }
    }

    private void createQueryForm(PrintWriter writer) {
        String queryForm = "<form action=\"\" method=\"get\">\n" +
                "    Search: <input type=\"text\" name=\"q\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\">\n" +
                "</form>";
        writer.println(queryForm);
    }

    private void createCreationForm(PrintWriter writer) {
        String form = "<form action=\"\" method=\"post\">\n" +
                "    FirstName: <input type=\"text\" name=\"firstName\">\n" +
                "    <br>\n" +
                "    LastName: <input type=\"text\" name=\"lastName\">\n" +
                "    <br>\n" +
                "    Age: <input type=\"number\" name=\"age\">\n" +
                "    <br>\n" +
                "    Gender: <input type=\"text\" name=\"gender\">\n" +
                "    <br>\n" +
                "    <input type=\"submit\">\n" +
                "</form>";
        writer.println(form);
    }


}
