package com.google.codeu.data;

import java.util.UUID;

/** A single ThreadComment posted by a user. */
public class ThreadComment {

  private UUID id;
  private String user;
  private String text;
  private UUID eventId;
  private long timestamp;

  /**
   * Constructs a new {@link ThreadComment} posted by {@code user} with {@code text}
   * content. Generates a random ID and uses the current system time for the
   * creation time.
   */
  public ThreadComment(String user, String text, UUID eventId) {
    this(UUID.randomUUID(), user, text, System.currentTimeMillis(), eventId);
  }

  public ThreadComment(UUID id, String user, String text, long timestamp, UUID eventId) {
    this.id = id;
    this.user = user;
    this.text = text;
    this.timestamp = timestamp;
    this.eventId = eventId;
  }

  public UUID getId() {
    return id;
  }

  public String getUser() {
    return user;
  }
  
  public UUID getEventId(){
    return eventId;
  }
  
  public String getText() {
    return text;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
