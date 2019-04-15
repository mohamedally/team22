<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<nav>
  <ul id="navigation">
    <li><a href="/">Home</a></li>
  
<%
  UserService userService = UserServiceFactory.getUserService();
  if (userService.isUserLoggedIn()) {
    String username = (String) userService.getCurrentUser().getEmail();
    request.setAttribute("Username",username);
%>
    <li><a href="/user-page.html?user=<%= username %>">Your Page</a></li>
    <li><a href="/map.jsp">Map</a></li>
    <li><a href="/feed.jsp">Public feed</a></li>
    <li><a href="/logout">Logout</a></li>
<% } else {   %>
    <li><a href="/login">Login</a></li>
<% } %>

  </ul>
</nav>