package com.n.twitter.api;

import java.io.IOException;

import model.SignUpResponse;
import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {
    boolean isSuccess = false;
    public static   String Token = "Bearer ";

    public boolean checkUser(String username, String password) {

        Reftofit usersAPI = new Reftofit();
        // Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.calls().checkUser(username, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                SignUpResponse signUpResponse=loginResponse.body();
                Token+=signUpResponse.getToken();


                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
