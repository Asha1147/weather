package com.anusha_peddina.WeatherApp;

import android.content.Context;
import android.content.SharedPreferences;

import com.anusha_peddina.WeatherApp.services.model.HomeScreenModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static double centigradeToFahrenheit(double celsius) {
        return ((celsius*9)/5)+32;
    }

    public static double fahrenheitToCentigrade(double fahrenheit) {
        return (( 5 *(fahrenheit - 32.0)) / 9.0);
    }
    public static String getFormattedHours(String epochTime) {
        DateTime dt = new DateTime( epochTime) ;
        return convertHoursTo12Hrs(Integer.parseInt(dt.hourOfDay().getAsShortText()));
    }

    public static List<HomeScreenModel> getCurrentHomeScreenModelList(Context context) {
        List<HomeScreenModel> currentHomeScreenModelList = new ArrayList<>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_info", Context.MODE_PRIVATE);
        String serializedObject = sharedPreferences.getString("home_list", null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<HomeScreenModel>>(){}.getType();
            currentHomeScreenModelList = gson.fromJson(serializedObject, type);
        }
        return currentHomeScreenModelList;
    }

    public static void saveHomeScreenModelList(Context context, List<HomeScreenModel> homeScreenModelList) {
        Gson gson = new Gson();
        String jsonCurProduct = gson.toJson(homeScreenModelList);

        SharedPreferences sharedPref = context.getSharedPreferences("app_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("home_list", jsonCurProduct);
        editor.commit();
    }

    public static String convertHoursTo12Hrs(int hours) {
        switch (hours) {
            case 1:
                return "1am";
            case 2:
                return "2am";
            case 3:
                return "3am";
            case 4:
                return "4am";
            case 5:
                return "5am";
            case 6:
                return "6am";
            case 7:
                return "7am";
            case 8:
                return "8am";
            case 9:
                return "9am";
            case 10:
                return "10am";
            case 11:
                return "11am";
            case 12:
                return "12pm";
            case 13:
                return "1pm";
            case 14:
                return "2pm";
            case 15:
                return "3pm";
            case 16:
                return "4pm";
            case 17:
                return "5pm";
            case 18:
                return "6pm";
            case 19:
                return "7pm";
            case 20:
                return "8pm";
            case 21:
                return "9pm";
            case 22:
                return "10pm";
            case 23:
                return "11pm";
            case 0:
                return "12am";
        }
        return String.valueOf(hours);
    }

    public static int getWeatherGif(int weatherIconNumber) {
        switch (weatherIconNumber) {
            case 1:
            case 2:
            case 3:
            case 30:
                return R.drawable.sunny;
            case 4:
            case 5:
            case 6:
                return R.drawable.sun_cloud1;
            case 7:
            case 8:
            case 11:
            case 31:
            case 32:
                return R.drawable.clouds;
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 39:
            case 40:
            case 41:
            case 42:
                return R.drawable.rainy;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 29:
            case 43:
            case 44:
                return R.drawable.snowy;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
                return R.drawable.clear1;
        }
        return R.drawable.clear1;
    }

    public static int getFontColor(int weatherIconNumber) {
        switch (weatherIconNumber) {
            case 1:
            case 2:
            case 3:
            case 30:
            case 7:
            case 8:
            case 11:
            case 31:
            case 32:
            case 4:
            case 5:
            case 6:
                return R.color.black;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 39:
            case 40:
            case 41:
            case 42:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 29:
            case 43:
            case 44:
                return R.color.white;
        }
        return R.color.black;
    }

    public static int getWeatherIcon(int weatherIconNumber) {
        switch (weatherIconNumber) {
            case 1:
                return R.drawable.w1;
            case 2:
                return R.drawable.w2;
            case 3:
                return R.drawable.w3;
            case 4:
                return R.drawable.w4;
            case 5:
                return R.drawable.w5;
            case 6:
                return R.drawable.w6;
            case 7:
                return R.drawable.w7;
            case 8:
                return R.drawable.w8;
            case 11:
                return R.drawable.w11;
            case 12:
                return R.drawable.w12;
            case 13:
                return R.drawable.w13;
            case 14:
                return R.drawable.w14;
            case 15:
                return R.drawable.w5;
            case 16:
                return R.drawable.w16;
            case 17:
                return R.drawable.w17;
            case 18:
                return R.drawable.w18;
            case 19:
                return R.drawable.w19;
            case 20:
                return R.drawable.w20;
            case 21:
                return R.drawable.w21;
            case 22:
                return R.drawable.w22;
            case 23:
                return R.drawable.w23;
            case 24:
                return R.drawable.w24;
            case 25:
                return R.drawable.w25;
            case 26:
                return R.drawable.w26;
            case 29:
                return R.drawable.w29;
            case 30:
                return R.drawable.w30;
            case 31:
                return R.drawable.w31;
            case 32:
                return R.drawable.w32;
            case 33:
                return R.drawable.w33;
            case 34:
                return R.drawable.w34;
            case 35:
                return R.drawable.w35;
            case 36:
                return R.drawable.w36;
            case 37:
                return R.drawable.w37;
            case 38:
                return R.drawable.w38;
            case 39:
                return R.drawable.w39;
            case 40:
                return R.drawable.w40;
            case 41:
                return R.drawable.w41;
            case 42:
                return R.drawable.w42;
            case 43:
                return R.drawable.w43;
            case 44:
                return R.drawable.w44;
        }
        return R.drawable.w1;
    }
}
