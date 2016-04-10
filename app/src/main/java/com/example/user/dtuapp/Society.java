package com.example.user.dtuapp;

/**
 * Created by user on 09-04-2016.
 */
public class Society {

    private String title;
    private int description;
    private String contactName;
    private String contactNumber;

    public int getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
