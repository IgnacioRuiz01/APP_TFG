package com.vedruna.tfg.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class TokenManager {
    private static TokenManager instance = null;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final String TAG = "TokenManager";

    private TokenManager(Context context) {
        prefs = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(Context context) {
        if (instance == null) {
            instance = new TokenManager(context);
        }
        return instance;
    }

    public synchronized  void saveToken(String token) {
        Log.d(TAG, "Saving token: " + token);
        editor.putString(Constants.PREFS_KEY_AUTH_TOKEN, token);
        editor.apply();
        Log.d(TAG, "Saved token: " + getToken());
    }

    public synchronized  String getToken() {
        String token = prefs.getString(Constants.PREFS_KEY_AUTH_TOKEN, null);
        Log.d(TAG, "Retrieved token: " + token);
        return token;
    }

    public synchronized  void clearToken() {
        Log.d(TAG, "Clearing token");
        editor.remove(Constants.PREFS_KEY_AUTH_TOKEN);
        editor.apply();
    }

    public String getUserId() {
        String token = getToken();
        if (token != null) {
            try {
                String[] parts = token.split("\\.");
                String claimsJson = new String(Base64.decode(parts[1], Base64.DEFAULT), StandardCharsets.UTF_8);
                JSONObject claims = new JSONObject(claimsJson);
                return claims.getString("user_id");
            } catch (JSONException e) {
                Log.e(TAG, "Error decoding JWT claims", e);
                return null;
            }
        } else {
            return null;
        }
    }


    public void setTestToken(String token) {
        saveToken(token);
    }
}
