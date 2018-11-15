package com.sda.servlets.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException { // init instancjonuje metody
        this.usersService = UsersService.instanceOf();
        // w ten sposób wołamy klasę, jesli jej instancja jest ona singletonem
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Integer id = Integer.valueOf(pathInfo.substring(1));
        User user = usersService.findById(id);
        displayUser(user, resp.getWriter());
    }

    private void displayUser(User user, PrintWriter writer){
        writer.println(user.getFirstName() + " " + user.getLastName());
        writer.println("Gender: " + user.getGender());
        writer.println("Age: " + user.getAge());
    }
}
