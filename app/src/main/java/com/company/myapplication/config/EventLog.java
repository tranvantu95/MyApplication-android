package com.company.myapplication.config;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class EventLog {

    private static EventLog instance;

    public static EventLog getInstance() {
        return instance;
    }

    public static void initialize(FirebaseAnalytics firebaseAnalytics) {
        instance = new EventLog(firebaseAnalytics);
    }

    private FirebaseAnalytics firebaseAnalytics;

    private EventLog(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

//    public void sendEvent() {
//        Bundle bundle = new Bundle();
//        bundle.putString("param1", param1);
//        bundle.putString("param2", param2);
//        firebaseAnalytics.logEvent("event-name", bundle);
//    }

}
