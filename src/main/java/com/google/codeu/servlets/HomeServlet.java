package com.google.codeu.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
    
    UserService userService = UserServiceFactory.getUserService();

    boolean isUserLoggedIn = userService.isUserLoggedIn();
    request.setAttribute("isUserLoggedIn", isUserLoggedIn);

    if (userService.isUserLoggedIn()) {
      String username = userService.getCurrentUser().getEmail();
      request.setAttribute("username", username);
    }

    request.getRequestDispatcher("/home.jsp").forward(request,response);
  }
}