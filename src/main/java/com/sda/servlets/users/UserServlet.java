package com.sda.servlets.users;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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
        PrintWriter writer = resp.getWriter();
        try {
            User user = usersService.findById(id);
            displayUser(user, writer);
        } catch (UserNotFoundException e){
            writer.println(e.getMessage());
            resp.setStatus(404); // 404 rzucamy kiedy nie ma zasobu, a nie kiedy zasób jest pusty.
            // Jeśli jest zasób pusty, to zwracamy 200
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        // reader.lines().forEach(stringBuilder::append);
        // to samo co na dole - aleternatywny zapis lamby
        reader.lines().forEach(e->stringBuilder.append(e));
        String json = stringBuilder.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json,User.class);
        Integer id = Integer.valueOf(req.getPathInfo().substring(1));
        user.setId(id);

        usersService.save(user);
    }

    private void displayUser(User user, PrintWriter writer){
        writer.println(user.getFirstName() + " " + user.getLastName());
        writer.println("Gender: " + user.getGender());
        writer.println("Age: " + user.getAge());
    }
}
