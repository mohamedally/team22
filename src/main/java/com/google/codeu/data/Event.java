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
    private List<String> ammenities;
    private String externalLink;
    private PublicType publicType;
    private int ownerId;
    private List<ThreadComment> thread;
    private long timeStamp;
    
    public Event(String speaker, String organization, Date eventDate, Location location, List<String> ammenities, String externalLink, PublicType publicType, int ownerId){
        this(UUID.randomUUID(), System.currentTimeMillis(), speaker, organization, eventDate, location, ammenities, externalLink, publicType, ownerId);
    }
    
    public Event(UUID id, long timeStamp, String speaker, String organization, Date eventDate, Location location, List<String> ammenities, String externalLink, PublicType publicType, int ownerId){
        this.eventId = id;
        this.speaker = speaker;
        this.organization = organization;
        this.eventDate = eventDate;
        this.location = location;
        this.ammenities = ammenities;
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

    public List<String> getAmmenities(){
        return ammenities;
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

    public long getTimeStamp(){
        return timeStamp;
    }
    
}
