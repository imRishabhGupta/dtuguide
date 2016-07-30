package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rohanagarwal94 on 13/7/16.
 */
public class CommentItem implements Parcelable{

    private String  id,comment, from, timeStamp;

    public CommentItem() {
    }


    protected CommentItem(Parcel in) {
        id = in.readString();
        comment = in.readString();
        from = in.readString();
        timeStamp = in.readString();
    }

    public static final Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel in) {
            return new CommentItem(in);
        }

        @Override
        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(from);
        dest.writeString(comment);
        dest.writeString(timeStamp);
    }
}
