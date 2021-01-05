package mfl.com.session.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class TestLogin {

    private Context context;
    private String auth = null;
    private String token = null;


    private SharedPreferences sharedPreferences;


    public TestLogin(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("TestLogin", Context.MODE_PRIVATE);
    }

    public void remove() {
        setLoginType(null);
        setToken(null);
    }


    public void setLoginType(String auth) {
        this.auth = auth;
        sharedPreferences.edit().putString("Auth", auth).commit();
    }

    public String getLoginType() {
        auth = sharedPreferences.getString("Auth", auth);
        return auth;
    }

    public void setToken(String token) {
        this.token = token;
        sharedPreferences.edit().putString("Token", token).commit();
    }

    public String getToken() {
        token = sharedPreferences.getString("Token", token);
        return token;
    }


}


