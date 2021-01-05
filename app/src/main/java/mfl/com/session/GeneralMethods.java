package mfl.com.session;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import mfl.com.session.checkNetwork.ConnectionDetector;
import mfl.com.session.sp.StoreLanguageData;


public class GeneralMethods {
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    private Context context;
    private StoreLanguageData storeLanguageData;

    public GeneralMethods(Context context) {
        this.context = context;
        cd = new ConnectionDetector(context);
        storeLanguageData = new StoreLanguageData(context);
    }

    public void changeLanguage() {
        Log.d("TAG", "Mohameek changeLanguage getAppLanguage():"+ storeLanguageData.getAppLanguage());

        Locale myLocale = new Locale(storeLanguageData.getAppLanguage());
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


    public void setDirection(RelativeLayout relativeLayout) {
        if (storeLanguageData.getAppLanguage().equals("en")) {
            relativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            relativeLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }

    }

    public Boolean checkInternet(Context context) {

        cd = new ConnectionDetector(context);
        isInternetPresent = cd.isConnectingToInternet();
        if (!isInternetPresent) {
            return false;
        }
        return true;
    }

    public String getDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        try {
            value = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mmaa");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        String dt = dateFormatter.format(value);

        return dt;
    }


}



