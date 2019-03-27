<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<nav>
  <ul id="navigation">
    <li><a href="/">Home</a></li>
  
<%
  UserService userService = UserServiceFactory.getUserService();
  if (userService.isUserLoggedIn()) {
    String username = (String) userService.getCurrentUser().getEmail();
%>
    <a href="/user-page.html?user=<%= username %>">Your Page</a>
    <a href="/logout">Logout</a>
<% } else {   %>
    <a href="/login">Login</a>
<% } %>

  </ul>
</nav>