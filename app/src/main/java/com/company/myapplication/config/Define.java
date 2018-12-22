package com.company.myapplication.config;

public class Define {

    private static final boolean USE_LOCAL = false;

    public static class API {
        public static final String BASE_URL =
                USE_LOCAL ? "http://192.168.23.102:8080/" : "https://chat-app-ging-nodejs.herokuapp.com/";
    }

    public static class Socket {
        public static final String HOST =
                USE_LOCAL ? "http://192.168.23.102:8080" : "https://chat-app-ging-nodejs.herokuapp.com";
    }

}
