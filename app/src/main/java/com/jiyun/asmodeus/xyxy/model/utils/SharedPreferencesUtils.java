package com.jiyun.asmodeus.xyxy.model.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


public class SharedPreferencesUtils {
    private static final String FILE_NAME = "XYXY";

    public static void setParam(Context context, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        }
        editor.commit();
    }

    public static Object getParam(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }


    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }


    public static void clearAll(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void saveId(Context context, String id) {
        if (context == null) {
            return;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.UserId, id);
        editor.commit();
    }
    public static void saveUserId(Context context, int id) {
        if (context == null) {
            return;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("loginId", id);
        editor.commit();
    }

    public static int getUserId(Context context) {


        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        int loginUserId = sharedPreferences.getInt("loginId", 0);
        return loginUserId;
    }

    public static boolean isLogin(Context context) {

        if (context == null) {
            return false;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);

        //TODO测试id
        return !TextUtils.isEmpty(sharedPreferences.getString(Constant.UserId, ""));
    }
    public static void loginOut(Context context) {

        if (context == null) {
            return ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Constant.UserId, "");
        edit.commit();
        //TODO测试id
    }

    public static void saveUsarInfo(Context context, String mobile, String url, String name) {
        if (context == null) {
            return;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("mobile", mobile);
        editor.putString("url", url);
        editor.putString("name", name);
        editor.commit();
    }

    public static String getUserMobile(Context context) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);

        return sharedPreferences.getString("mobile", "");

    }

    public static String getUserInfo(Context context) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);

        return sharedPreferences.getString("name", "");

    }

    public static String getUserInfoUrl(Context context) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);


        return sharedPreferences.getString("url", "");


    }
}

