package com.company.myapplication.utils;

import com.facebook.AccessToken;

public class FBUtils {

    public static boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

}
