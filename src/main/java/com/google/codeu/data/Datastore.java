/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

/** Provides access to the data stored in Datastore. */
public class Datastore {

  private DatastoreService datastore;

  public Datastore() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  /** Stores the Message in Datastore. */
  public void storeMessage(Message message) {
    Entity messageEntity = new Entity("Message", message.getId().toString());
    messageEntity.setProperty("user", message.getUser());
    messageEntity.setProperty("text", message.getText());
    messageEntity.setProperty("timestamp", message.getTimestamp());
    messageEntity.setProperty("recipient", message.getRecipient());
    datastore.put(messageEntity);
  }

  /**
  * Gets messages received by a specific user (recipient).
  *
  * @return a list of messages received by a user, or empty list if user has
  *         never received a message. List is sorted by time descending.
  */
  public List<Message> getMessages(String recipient) {
    Query query =
        new Query("Message")
            .setFilter(new Query.FilterPredicate("recipient", FilterOperator.EQUAL, recipient))
            .addSort("timestamp", SortDirection.DESCENDING);

    return returnMessages(query, recipient);
  }
    
  /**
  * Gets all messages without user filtering
  *
  * @return a list of messages , or empty list if no message has been sent. List is sorted by time descending.
  */
  public List<Message> getAllMessages() {
    Query query = new Query("Message").addSort("timestamp", SortDirection.DESCENDING);

      return returnMessages(query, null);
    }

  private List<Message> returnMessages(Query query, String recipient) {
    List<Message> messages = new ArrayList<>();
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID id = UUID.fromString(idString);
        String text = (String) entity.getProperty("text");
        long timestamp = (long) entity.getProperty("timestamp");
        String user = (String) entity.getProperty("user");
        recipient = recipient != null ? recipient : (String) entity.getProperty("recipient");

        Message message = new Message(id, user, text, timestamp, recipient);
        messages.add(message);
      //An exception can occur here for multiple reasons (Type casting error, any property not existing, key error, etc...)
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }

    return messages;
  }
    
  /**
  * Gets all created user markers
  *
  * @return a list of markers created by any user, or empty list if none were created.
  */
  public List<UserMarker> getMarkers() {
    List<UserMarker> markers = new ArrayList<>();

    Query query = new Query("UserMarker");
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        double lat = (double) entity.getProperty("lat");
        double lng = (double) entity.getProperty("lng");    
        String content = (String) entity.getProperty("content");

        UserMarker marker = new UserMarker(lat, lng, content);
        markers.add(marker);
      //An exception can occur here for multiple reasons (Type casting error, any property not existing, key error, etc...)
      } catch (Exception e) {
        System.err.println("Error reading marker.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return markers;
  }
    
  /**
  * Stores an user created marker
  */
  public void storeMarker(UserMarker marker) {
    Entity markerEntity = new Entity("UserMarker");
    markerEntity.setProperty("lat", marker.getLat());
    markerEntity.setProperty("lng", marker.getLng());
    markerEntity.setProperty("content", marker.getContent());
    datastore.put(markerEntity);
  }
    
  /**
  * Gets all created events without a filter.
  *
  * @return a list of events, or empty list if none were created.
  */
  public List<Event> getEvents(){
    List<Event> events = new ArrayList<>();

    Query query = new Query("Event");
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID eventId = UUID.fromString(idString);
        String speaker =  (String) entity.getProperty("speaker");
        String organization =  (String) entity.getProperty("organization");
        Date eventDate =  (Date) entity.getProperty("eventDate");
        Location location =  (Location) entity.getProperty("location");
        List<String> amenities =  getAmenities(eventId);
        String externalLink =  (String) entity.getProperty("externalLink");
        PublicType publicType =  (PublicType) entity.getProperty("publicType");
        int ownerId =  (int) entity.getProperty("ownerId");
        List<ThreadComment> thread =  getThread(eventId);
        long timeStamp =  (long) entity.getProperty("timestamp");

        Event event = new Event(eventId, timeStamp, speaker, organization, eventDate, location, amenities, externalLink, publicType, ownerId);
        event.copyThread(thread);
        events.add(event);
      //An exception can occur here for multiple reasons (Type casting error, any property not existing, key error, etc...)
      } catch (Exception e) {
        System.err.println("Error reading event.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }

    return events;
  }

  private List<String> getAmenities(UUID eventId){
    List<String> amenities = new ArrayList<>();

    Query query = new Query("Amenity")
        .setFilter(new Query.FilterPredicate("eventId", FilterOperator.EQUAL, eventId.toString()))
        .addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String text = (String) entity.getProperty("amenity");

        amenities.add(text);
      //An exception can occur here for multiple reasons (Type casting error, any property not existing, key error, etc...)
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }

    return amenities;
  }

  private List<ThreadComment> getThread(UUID eventId){
    List<ThreadComment> thread = new ArrayList<>();

    Query query = new Query("ThreadComment")
        .setFilter(new Query.FilterPredicate("eventId", FilterOperator.EQUAL, eventId.toString()))
        .addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    for (Entity entity : results.asIterable()) {
      try {
        String idString = entity.getKey().getName();
        UUID id = UUID.fromString(idString);
        String text = (String) entity.getProperty("text");
        long timestamp = (long) entity.getProperty("timestamp");
        String user = (String) entity.getProperty("user");
        ThreadComment comment = new ThreadComment(id, user, text, timestamp, eventId);

        thread.add(comment);
      //An exception can occur here for multiple reasons (Type casting error, any property not existing, key error, etc...)
      } catch (Exception e) {
        System.err.println("Error reading message.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }

    return thread;
  }

  /**
  * Stores an event to the datastore
  */
  public void storeEvent(Event event){
    Entity eventEntity = new Entity("Event", event.getEventId().toString());
    eventEntity.setProperty("speaker", event.getSpeaker());
    eventEntity.setProperty("organization", event.getOrganization());
    eventEntity.setProperty("eventDate", event.getEventDate());
    eventEntity.setProperty("location", event.getLocation());
    for (String am: event.getAmenities()){
      storeAmenity(am, event.getEventId());
    }
    eventEntity.setProperty("externalLink", event.getExternalLink());
    eventEntity.setProperty("publicType", event.getPublicType());
    eventEntity.setProperty("ownerId", event.getOwnerId());
    for (ThreadComment cm: event.getThread()){
      storeThreadComment(cm);
    }
    eventEntity.setProperty("timeStamp", event.getTimeStamp());
    datastore.put(eventEntity);
  }   

  private void storeAmenity(String amenity,UUID eventId){
    Entity amenityEntity = new Entity("Amenity");
    amenityEntity.setProperty("eventId", eventId.toString());
    amenityEntity.setProperty("amenity", amenity);
    datastore.put(amenityEntity);
  }

  /**
  * Stores a new comment to an event's thread
  */
  public void storeThreadComment(ThreadComment comment){
    Entity threadCommentEntity = new Entity("ThreadComment", comment.getId().toString());
    threadCommentEntity.setProperty("eventId", comment.getEventId().toString());
    threadCommentEntity.setProperty("user", comment.getUser());
    threadCommentEntity.setProperty("text", comment.getText());
    threadCommentEntity.setProperty("timestamp", comment.getTimestamp());
    datastore.put(threadCommentEntity);
  }

  /** Stores the User in Datastore. */
  public void storeUser(User user) {
    Entity userEntity = new Entity("User", user.getEmail());
    userEntity.setProperty("email", user.getEmail());
    userEntity.setProperty("aboutMe", user.getAboutMe());
    datastore.put(userEntity);
  }
   
  /**
  * Returns the User owned by the email address, or
  * null if no matching User was found.
  */
  public User getUser(String email) {
   
    Query query = new Query("User")
      .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
    PreparedQuery results = datastore.prepare(query);
    Entity userEntity = results.asSingleEntity();
    if(userEntity == null) {
      return null;
    }
    
    String aboutMe = (String) userEntity.getProperty("aboutMe");
    User user = new User(email, aboutMe);
    
    return user;
  }
}
