package com.example.hello.shooteralert;

import android.net.Uri;



public class User {
    private String user_id;
    private String user_name;
    private Uri user_avatar_URI;

    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String user_id, String user_name, Uri user_avatar_URI){
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_avatar_URI = user_avatar_URI;
    }
    

}
