package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohanpc on 4/10/2016.
 */
public class FeedItem {

    private String  id,status, image, timeStamp, url;
    private ArrayList<CommentItem> commentItems = new ArrayList<>();

    public FeedItem() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImge() {
        return image;
    }

    public void setImge(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<CommentItem> getCommentItems() {
        return commentItems;
    }

    public int getCommentsSize()
    {
        return commentItems.size();
    }

    public void setCommentItems(ArrayList<CommentItem> commentItems)
    {
        this.commentItems=commentItems;
    }

}