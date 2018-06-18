package com.example.abdalla_maged.openglapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import network.fastAndroidNetwork.BaseNetWorkApi;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        mGLSurfaceView = new GLSurfaceView(this);
        if (supportsEs2) {
            // Request an OpenGL ES 2.0 compatible context.
            mGLSurfaceView.setEGLContextClientVersion(2);

            // Set the renderer to our demo renderer, defined below.
            mGLSurfaceView.setRenderer(new MyGLRenderer());
        } else {
            // This is where you could create an OpenGL ES 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.
            return;
        }
        makeSimpeApi();

        setContentView(mGLSurfaceView);
    }


    public void makeSimpeApi() {
        String url = "http://dev.spade.studio/nap/public/api/v1/en/chairman";
        BaseNetWorkApi.makeGetquestRequestTemp("en", url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Example -> {
                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Log.e("MainActivity","Error ----->"+throwable.getMessage());
                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }


    @Override
    protected void onResume() {
        // The activity must call the GL surface view's onResume() on activity onResume().
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        // The activity must call the GL surface view's onPause() on activity onPause().
        super.onPause();
        mGLSurfaceView.onPause();
    }

}
