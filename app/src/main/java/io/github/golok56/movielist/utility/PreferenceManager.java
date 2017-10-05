package io.github.golok56.movielist.utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Satria Adi Putra
 */
public class PreferenceManager {

    private static final String PREFERENCE_NAME = "movie_list_pref";
    private static final String IS_LOGIN = "is_login";

    private SharedPreferences mPref;

    public PreferenceManager(Context ctx) {
        mPref = ctx.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setLogin(boolean isLogin) {
        mPref.edit()
                .putBoolean(IS_LOGIN, isLogin)
                .apply();
    }

    public boolean isLogin(){
        return mPref.getBoolean(IS_LOGIN, false);
    }

}
