package com.example.abdalla_maged.openglapp;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by abdalla-maged on 3/18/18.
 */
class MyGLSurfaceView extends GLSurfaceView {

    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);

//        set the render mode to only draw the view when there is a change to your drawing data
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}