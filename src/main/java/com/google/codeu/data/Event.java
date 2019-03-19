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
        this.Event(UUID.randomUUID(), System.currentTimeMillis(), speaker, organization, eventDate, location, ammenities, externalLink, publicType, ownerId);
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
    
    
}
