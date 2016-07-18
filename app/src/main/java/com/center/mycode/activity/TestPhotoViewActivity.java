package com.center.mycode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.center.mycode.R;
import com.center.mycode.view.photoview.PhotoView;
import com.center.mycode.view.photoview.multitouch.RotateGestureDetector;

import static android.view.MotionEvent.ACTION_CANCEL;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

public class TestPhotoViewActivity extends AppCompatActivity
{
    
    private PhotoView mPhotoView;
    
    private float mRotationDegreesOld = 0.f;
    
    private float mRotationDegrees = 0.f;
    
    private RotateGestureDetector mRotateDetector;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_photoview);
        init();
    }
    
    private void init()
    {
        mPhotoView = (PhotoView)findViewById(R.id.photo_view);
        mPhotoView.setImageResource(R.drawable.title_image);
        mRotateDetector = new RotateGestureDetector(this, new RotateListener());
        mPhotoView.setOnNewTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent ev)
            {
                mRotateDetector.onTouchEvent(ev);
                mPhotoView.setRotationBy(mRotationDegrees - mRotationDegreesOld);
                mRotationDegreesOld = mRotationDegrees;
                switch (ev.getAction())
                {
                    case ACTION_DOWN:
                        break;
                    case ACTION_CANCEL:
                    case ACTION_UP:
                        int r = (int)(mRotationDegrees / 90);
                        double s = mRotationDegrees - r * 90;
                        if (Math.abs(s) > 45)
                        {
                            r = r + (s >= 0 ? 1 : -1);
                        }
                        mRotationDegrees = (float)(r * 90);
                        mPhotoView.setRotationBy(mRotationDegrees - mRotationDegreesOld);
                        mPhotoView.setAllScaleByRotate90(r * 90);
                        mRotationDegreesOld = mRotationDegrees;
                        break;
                }
                return true; // indicate event was handled
            }
        });
    }
    
    private class RotateListener extends RotateGestureDetector.SimpleOnRotateGestureListener
    {
        @Override
        public boolean onRotate(RotateGestureDetector detector)
        {
            mRotationDegrees -= detector.getRotationDegreesDelta();
            return true;
        }
    }
    
    public void doFinish(View v)
    {
        finish();
    }
}
