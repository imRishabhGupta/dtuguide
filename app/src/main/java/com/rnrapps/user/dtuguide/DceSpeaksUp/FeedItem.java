package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohanpc on 4/10/2016.
 */
public class FeedItem implements Parcelable {

    private String  id,status, image, timeStamp, url;
    private ArrayList<CommentItem> commentItems = new ArrayList<>();

    public FeedItem() {
    }


    protected FeedItem(Parcel in) {
        id = in.readString();
        status = in.readString();
        image = in.readString();
        timeStamp = in.readString();
        url = in.readString();
        if(commentItems!=null) {
//        in.readTypedList(commentItems,CommentItem.CREATOR);
            in.createTypedArrayList(CommentItem.CREATOR);
        }
    }

    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };

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

    public void setCommentItems(ArrayList<CommentItem> commentItems)
    {
        this.commentItems=commentItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(status);
        dest.writeString(image);
        dest.writeString(timeStamp);
        dest.writeString(url);
        dest.writeTypedList(commentItems);
    }
}