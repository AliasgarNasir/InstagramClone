package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("PQdcO8tNjq2XL5wqSHlzucqhj77VhWivbo4pmgq3")
                // if defined
                .clientKey("FYfI1JebZstCBr9O9zCq8iMfYfbC8ZN7Ib3WstHu")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
