package com.n.twitter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.biplav.twitterassignment.adapter.TweetAdapter;
import com.biplav.twitterassignment.api.Reftofit;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import model.TweetResponse;
import model.UserInfoList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    //UploadProfile cm = new UploadProfile();
    ImageView imageView;
    RecyclerView recyclerView;
    String imgPath;
    TextView tvusername;
    LoginActivity la = new LoginActivity();
    //SplashScreen sc=new SplashScreen();
    Button btnLogout;
    public static final String base_url = "http://10.0.2.2:3002/";
    String imagePath = base_url + "public/uploads/" ;
    String Token= LoginActivity.Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       // imageView=findViewById(R.id.circleImage);
        tvusername=findViewById(R.id.textView2);
        recyclerView=findViewById(R.id.recycle);

        imageView = (ImageView) findViewById(R.id.circleImage);



      //  loadCurrentUser();
        Reftofit usersAPI1 = new Reftofit();

        Call<List<TweetResponse>> userCall1 = usersAPI1.calls().GetTweet( Token );
        System.out.println("TwitterList: " + Token );
        userCall1.enqueue( new Callback<List<TweetResponse>>() {
            @Override
            public void onResponse(Call<List<TweetResponse>> call, Response<List<TweetResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT ).show();
                    // System.out.println(response.toString());
                    return;
                }
                List<TweetResponse> tweetMS = response.body();
               // System.out.println("Twwwts"+tweetMS.toString());

                TweetAdapter tweetAdapter = new TweetAdapter( DashboardActivity.this, tweetMS );
                recyclerView.setAdapter( tweetAdapter );
                recyclerView.setLayoutManager( new LinearLayoutManager( DashboardActivity.this ) );
            }

            @Override
            public void onFailure(Call<List<TweetResponse>> call, Throwable t) {
                Toast.makeText( DashboardActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                System.out.println("List error:" +t.getLocalizedMessage());

            }
        } );


        Reftofit usersAPI = new Reftofit();

        Call<UserInfoList> userCall = usersAPI.calls().getUser(Token);
        System.out.println("User details: " + Token);
        userCall.enqueue(new Callback<UserInfoList>() {
            @Override
            public void onResponse(Call<UserInfoList> call, Response<UserInfoList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();

                    return;
                }
                UserInfoList userInfo = response.body();
                // Toast.makeText( DashBoard.this, " "+userInfo.get_id(), Toast.LENGTH_SHORT ).show();
                tvusername.setText("Welcome: "+userInfo.getUsername());

               imgPath = imagePath + userInfo.getImage();
                Toast.makeText(DashboardActivity.this, ""+imgPath, Toast.LENGTH_SHORT).show();
             //   Glide.with(getApplicationContext()).load(imgPath).into(imageView);
                try {
                    URL url = new URL(imgPath);
                    Bitmap imageBitmap= BitmapFactory.decodeStream((InputStream) url.getContent());
                    //   System.out.println("imageBitMap: "+imageBitmap);
                          imageView.setImageBitmap(imageBitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserInfoList> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("error dashborad" + t.getLocalizedMessage());

            }
        });


        btnLogout=findViewById(R.id.btn_D_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

//   private void loadCurrentUser() {
//
//        if (la.Token.isEmpty()) {
//           // token = cm.token;
//            token=la.Token;
//          //  Toast.makeText(DashboardActivity.this, "token " +token, Toast.LENGTH_SHORT).show();
//
//        }
//        else {
//            token = la.Token;
//             Toast.makeText(DashboardActivity.this, "token " +token, Toast.LENGTH_SHORT).show();
//
//        }



  //  }
    }
