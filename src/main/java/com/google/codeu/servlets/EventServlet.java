package com.google.codeu.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Event;
import com.google.codeu.data.Location;
import com.google.codeu.data.ThreadComment;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/** Handles fetching and saving {@link Event} instances. */
@WebServlet("/events")
public class EventServlet extends HttpServlet{
    
    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }
  
    private Event toEvent(){
        Event event = new Event();
        return event;
    }
}
