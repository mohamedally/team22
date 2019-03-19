package com.google.codeu.data;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

enum PublicType{
    ALLPUBLIC, CAMPUSWIDE, INVITEONLY;
}

public class Event {
    
    private UUID eventId;
    private String speaker;
    private String organization;
    private Date eventDate;
    private Location location;
    private List<String> amenities;
    private String externalLink;
    private PublicType publicType;
    private int ownerId;
    private List<ThreadComment> thread;
    private long timeStamp;
    
    public Event(String speaker, String organization, Date eventDate, Location location, List<String> amenities, String externalLink, PublicType publicType, int ownerId){
        this(UUID.randomUUID(), System.currentTimeMillis(), speaker, organization, eventDate, location, amenities, externalLink, publicType, ownerId);
    }
    
    public Event(UUID id, long timeStamp, String speaker, String organization, Date eventDate, Location location, List<String> amenities, String externalLink, PublicType publicType, int ownerId){
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
        this.timeStamp = timeStamp;
    }
    
    public UUID getEventId(){
        return eventId;
    }

    public String getSpeaker(){
        return speaker;
    }

    public String getOrganization(){
        return organization;
    }

    public Date getEventDate(){
        return eventDate;
    }

    public Location getLocation(){
        return location;
    }

    public List<String> getAmenities(){
        return amenities;
    }

    public String getExternalLink(){
        return externalLink;
    }

    public PublicType getPublicType(){
        return publicType;
    }

    public int getOwnerId(){
        return ownerId;
    }

    public List<ThreadComment> getThread(){
        return thread;
    }
    
    public void addThreadComment(ThreadComment comment){
        thread.add(comment);
    }
    
    public void copyThread(List<ThreadComment> thrd){
        thread = thrd;
    }

    public long getTimeStamp(){
        return timeStamp;
    }
    
}
