<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/home">Team 22</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="/home">Home <span class="sr-only">(current)</span></a>
  
<%
  UserService userService = UserServiceFactory.getUserService();
  if (userService.isUserLoggedIn()) {
    String username = (String) userService.getCurrentUser().getEmail();
    request.setAttribute("Username",username);
%>

    <a class="nav-item nav-link" href="/user-page.html?user=<%= username %>">Your page</a>
    <a class="nav-item nav-link" href="/map.jsp">Map</a>
    <a class="nav-item nav-link" href="/feed.jsp">Public feed</a>
    <a class="nav-item nav-link" href="/logout">Logout</a>
<% } else {   %>
    <a class="nav-item nav-link" href="/login">Login</a>
<% } %>

  </div>
  </div>
</nav>

  
   