<%@ page import="com.sda.servlets.links.Link" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sda.servlets.users.UsersService" %>
<h1>Hello world</h1>

<%!
    String message = "Ala ma kota";
    Link link = new Link("https://www.google.pl/", "Google");
    Integer age = 15;
    UsersService usersService = UsersService.instanceOf();
%>

<%= "test"%>

<h1>
    <%= message%>
    <%= "Ala ma kota2"%>
    <%= link.getText()%>
</h1>

<%
    List<String> list= new ArrayList<>();
    list.add("Jan");
    list.add("Anna");
    message = "test";
%>

<% if(age>18){%>
    <h2>Jestes pelnoletni</h2>
<%}else {%>
    <h2>Jestes nipelnoletni</h2>
<% } %>

<%
    if (age>18){
        out.println("<h2>Jestes pelnoletni</h2>");
    }else {
        out.println("<h2>Jestes niepelnoletni</h2>");
    }
%>