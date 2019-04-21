package com.google.codeu.servlets;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.Event;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles fetching and saving {@link Event} instances. */
@WebServlet("/events")
public class EventServlet extends HttpServlet{
    
  private Datastore datastore;

  @Override
  public void init() {
    datastore = new Datastore();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Gson gson = new Gson();

    BufferedReader inputBr = request.getReader();

    Event[] events = gson.fromJson(inputBr, Event[].class);

    datastore.storeEvent(event[0]);
    
    response.addHeader("Storage_confirmation", event.getEventId().toString());
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    response.setContentType("application/json");    

    List<Event> events = datastore.getEvents();
    Gson gson = new Gson();
    String json = gson.toJson(events);

    response.getWriter().println(json);
  }

}
