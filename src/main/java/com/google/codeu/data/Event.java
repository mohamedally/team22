package com.google.codeu.data;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

enum PublicType {
  ALLPUBLIC, CAMPUSWIDE, INVITEONLY;
}

public class Event {

  private UUID eventId;
  private String speaker;
  private String organization;
  private String eventDate; // This change was made to stop the errors when serializing back and forth from
                            // json format
  private Location location;
  private List<String> amenities;
  private String externalLink;
  private PublicType publicType;
  private long ownerId;
  private List<ThreadComment> thread;
  // private long timeStamp;
  // System.currentTimeMillis()

  public Event(String speaker, String organization, String eventDate, Location location, List<String> amenities,
      String externalLink, PublicType publicType, long ownerId) {
    this(UUID.randomUUID(), speaker, organization, eventDate, location, amenities, externalLink, publicType, ownerId);
  }

  // long timeStamp
  public Event(UUID id, String speaker, String organization, String eventDate, Location location,
      List<String> amenities, String externalLink, PublicType publicType, long ownerId) {
    this.eventId = id;
    this.speaker = speaker;
    this.organization = organization;
    this.eventDate = eventDate;
    this.location = location;
    this.amenities = amenities;
    this.externalLink = externalLink;
    this.publicType = publicType;
    this.ownerId = ownerId;
    this.thread = new ArrayList<ThreadComment>();
    // this.timeStamp = timeStamp;
  }

  public UUID getEventId() {
    if (eventId == null) {
      this.eventId = UUID.randomUUID();
    }
    return eventId;
  }

  public String getSpeaker() {
    return speaker;
  }

  public String getOrganization() {
    return organization;
  }

  public String getEventDate() {
    return eventDate;
  }

  public Location getLocation() {
    return location;
  }

  public List<String> getAmenities() {
    return amenities;
  }

  public String getExternalLink() {
    return externalLink;
  }

  public PublicType getPublicType() {
    return publicType;
  }

  public long getOwnerId() {
    return ownerId;
  }

  public List<ThreadComment> getThread() {
    return thread;
  }

  public void addThreadComment(ThreadComment comment) {
    thread.add(comment);
  }

  public void copyThread(List<ThreadComment> thrd) {
    thread = thrd;
  }

  // public long getTimeStamp() {
  // return timeStamp;
  // }

}
