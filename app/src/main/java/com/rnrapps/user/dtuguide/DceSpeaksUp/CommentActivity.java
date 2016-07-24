package com.rnrapps.user.dtuguide.DceSpeaksUp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.rnrapps.user.dtuguide.AppController;
import com.rnrapps.user.dtuguide.R;
import com.rnrapps.user.dtuguide.Utils;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private CommentsAdapter commentsAdapter;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        final ListView commentListView=(ListView)findViewById(R.id.commentslist);
        TextView timestamp = (TextView)findViewById(R.id.timestamp);
        TextView statusMsg = (TextView)findViewById(R.id.txtStatusMsg);
        TextView url = (TextView)findViewById(R.id.txtUrl);

        FeedImageView feedImageView = (FeedImageView)findViewById(R.id.feedImage1);

        ArrayList<CommentItem> commentItems;
        FeedItem feedItem;
        commentItems=new ArrayList<>();
        Bundle bundle=getIntent().getExtras();
        if (bundle.getParcelableArrayList("comments")!=null)
           commentItems=bundle.getParcelableArrayList("comments");
        feedItem=bundle.getParcelable("status");

        // Converting timestamp into x ago format
        String a=feedItem.getTimeStamp();
        timestamp.setText(Utils.getTimeFromTimestamp(a));

        // Check for empty status message
        if (!TextUtils.isEmpty(feedItem.getStatus())) {
            statusMsg.setText(feedItem.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (feedItem.getUrl() != null) {
            url.setText(Html.fromHtml("<a href=\"" + feedItem.getUrl() + "\">"
                    + feedItem.getUrl() + "</a> "));

            // Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

        // Feed image
        if (feedItem.getImge() != null) {
            feedImageView.setImageUrl(feedItem.getImge(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else {
            feedImageView.setVisibility(View.GONE);
        }

        if(commentItems.size()!=0) {
            commentsAdapter = new CommentsAdapter(this, commentItems);
            commentsAdapter.notifyDataSetChanged();
            commentListView.setAdapter(commentsAdapter);
        }
    }

}
