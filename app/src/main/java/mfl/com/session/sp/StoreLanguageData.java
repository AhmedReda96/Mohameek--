package mfl.com.session.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class StoreLanguageData {
    private SharedPreferences sharedPreferences;
    private String appLanguage = "ar";

    public StoreLanguageData(Context context) {
       sharedPreferences=context.getSharedPreferences("StoreData", Context.MODE_PRIVATE);
    }

    public String getAppLanguage() {

        appLanguage=sharedPreferences.getString("appLanguage",appLanguage);
        return appLanguage;
    }

    public void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
        sharedPreferences.edit().putString("appLanguage",appLanguage).apply();
    }
}
