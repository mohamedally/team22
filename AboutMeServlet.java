package com.google.codeu.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.User;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Handles fetching and saving user data.
 */
@WebServlet("/about")
public class AboutMeServlet extends HttpServlet {

  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  /**
   * Responds with the "about me" section for a particular user.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType("text/html");

    String user = request.getParameter("user");

    if(user == null || user.equals("")) {
      // Request is invalid, return empty response
      return;
    }

    User userData = datastore.getUser(user);

    if(!userData || !userData.getAboutMe()) {
      return;
    }

    response.getOutputStream().println(userData.getAboutMe());
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    UserService userService = UserServiceFactory.getUserService();
    if (!userService.isUserLoggedIn()) {
      response.sendRedirect("/index.html");
      return;
    }

    String userEmail = userService.getCurrentUser().getEmail();

    String userEnteredContent = request.getParameter("about-me");
    Whitelist whitelist = Whitelist.simpleText();
    whitelist.addAttributes("b", "strong", "i", "em", "mark", "small", "del", "sup", "sub");
    String aboutMe = Jsoup.clean(userEnteredContent, whitelist);

    User user = new User(userEmail, aboutMe);
    datastore.storeUser(user);

    response.sendRedirect("/user-page.html?user=" + userEmail);
  }
}
