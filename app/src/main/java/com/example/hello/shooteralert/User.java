package com.example.hello.shooteralert;



public class User {
    public String user_name;
    public String user_avatar_URI;
    public double latitude;
    public double longtitude;

    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avatar_URI() {
        return user_avatar_URI;
    }

    public void setUser_avatar_URI(String user_avatar_URI) {
        this.user_avatar_URI = user_avatar_URI;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
