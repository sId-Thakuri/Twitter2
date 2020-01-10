package com.n.twitter.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.n.twitter.R;
import com.n.twitter.model.TweetResponse;
import com.n.twitter.strictMode.StrictModeClass;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetList>{
    Context context;
    List<TweetResponse> dataSetList;
    public static final String base_url = "http://10.0.2.2:3002/";
    String imagePath = base_url + "public/uploads/" ;

    public TweetAdapter(Context context, List<TweetResponse> dataSetList) {
        this.context = context;
        this.dataSetList = dataSetList;
    }

    @NonNull
    @Override
    public TweetAdapter.TweetList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.tweets_layout, parent, false );
        return new TweetAdapter.TweetList( view );

    }

    @Override
    public void onBindViewHolder(@NonNull TweetAdapter.TweetList holder, int position) {
       TweetResponse tm = dataSetList.get( position );
       holder.fullname.setText("@"+ tm.getUsername() );
        holder.tweet.setText( tm.getTweet());
        holder.like.setText(""+tm.getLike());
        holder.dislike.setText(""+tm.getDislike());
        holder.comment.setText(""+tm.getComment());
        StrictModeClass.StrictMode();
        String imgPath = imagePath+ tm.getTweetImage() ;
        try {
            URL url = new URL(imgPath);
            holder.m_img.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return dataSetList.size();
    }

    public class TweetList extends RecyclerView.ViewHolder {
        ImageView m_img;
        TextView fullname,tweet,like, dislike,comment;
        public TweetList(@NonNull View itemView) {
            super( itemView );
//            p_img=itemView.findViewById( R.id.P_img );
           m_img=itemView.findViewById( R.id.M_img );
            tweet=itemView.findViewById( R.id.M_txt );
            fullname=itemView.findViewById( R.id.H_txt );
            like=itemView.findViewById(R.id.tvlike);
            dislike=itemView.findViewById(R.id.tvdislike);
            comment=itemView.findViewById(R.id.tvcomment);
        }
    }
}
