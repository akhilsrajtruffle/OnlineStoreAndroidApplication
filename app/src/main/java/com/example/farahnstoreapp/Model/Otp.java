package com.example.farahnstoreapp.Model;

import com.google.gson.annotations.SerializedName;

public class Otp {

    @SerializedName("state")
    private String status;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
