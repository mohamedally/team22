package com.google.codeu.data;

import java.util.List;

public class Location {
    
  private double lat;
  private double lng;
  private String locationName;
  private String country;
  private String state;
  private String city;
  private int zipcode;
  private List<String> adressLines;

  public Location(double lat, double lng, String name, String country, String state, String city, int zipcode, List<String> adressLines){
    this.lat = lat;
    this.lng = lng;
    this.locationName = name;
    this.country = country;
    this.state = state;
    this.city = city;
    this.zipcode = zipcode;
    this.adressLines = adressLines;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  public String getLocationName(){
    return locationName;
  }

  public String getCountry(){
    return country;
  }

  public String getState(){
    return state;
  }

  public String getCity(){
    return city;
  }

  public int getZipcode(){
    return zipcode;
  }

  public List<String> getAdressLines(){
    return adressLines;
  }

  public static class Builder{
    private double lat;
    private double lng;
    private String locationName;
    private String country;
    private String state;
    private String city;
    private int zipcode;
    private List<String> adressLines;

    public Builder(){}

    public Builder setLat(double lat){
      this.lat = lat;
      return this;
    }

    public Builder setLng(double lng){
      this.lng = lng;
      return this;
    }

    public Builder setLocationName(String locationName){
      this.locationName = locationName;
      return this;
    }

    public Builder setCountry(String country){
      this.country = country;
      return this;
    }

    public Builder setState(String state){
      this.state = state;
      return this;
    }

    public Builder setCity(String city){
      this.city = city;
      return this;
    }

    public Builder setZipcode(int zipcode){
      this.zipcode = zipcode;
      return this;
    }

    public Builder setAdressLines(List<String> adressLines){
      this.adressLines = adressLines;
      return this;
    }

    public Location build(){
      return new Location(lat, lng, locationName, country, state, city, zipcode, adressLines);
    }
  } 
}
