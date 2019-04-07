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
  
 /** Returns the total number of messages for all users. */
 public int getTotalMessageCount(){
   Query query = new Query("Message");
   PreparedQuery results = datastore.prepare(query);
   return results.countEntities(FetchOptions.Builder.withLimit(1000));
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

  /** Returns all messages in Datastore. */
  public List<Message> getAllMessages() {
    Query query = new Query("Message").addSort("timestamp", SortDirection.DESCENDING);
    
    return returnMessages(query, null);
  }

  /** Returns all messages in Datastore for a specific recipient. */
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
      } catch (Exception e) {
          System.err.println("Error reading message.");
          System.err.println(entity.toString());
          e.printStackTrace();
      }
    }
    
    return messages;
  }
  
  /** Returns all user defined markers in Datastore. */
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
      } catch (Exception e) {
        System.err.println("Error reading marker.");
        System.err.println(entity.toString());
        e.printStackTrace();
      }
    }
    return markers;
  }
  
  /** Stores the User defined marker in Datastore. */
  public void storeMarker(UserMarker marker) {
    Entity markerEntity = new Entity("UserMarker");
    markerEntity.setProperty("lat", marker.getLat());
    markerEntity.setProperty("lng", marker.getLng());
    markerEntity.setProperty("content", marker.getContent());
    datastore.put(markerEntity);
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
