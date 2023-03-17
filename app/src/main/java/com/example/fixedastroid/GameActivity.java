package com.example.fixedastroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    DrawSurfaceView ds;
    TextView counterTextView;
    Thread thread;
    FrameLayout frame;
    Button btnD;
    Button btnU;
    Button btnR;
    Button btnL;
    Button run;
    Button pause;
    int co =0;

    //קאונטר הצלחות
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btnD = findViewById(R.id.btnD);
        btnU = findViewById(R.id.btnU);
        btnR = findViewById(R.id.btnR);
        btnL = findViewById(R.id.btnL);
        frame = findViewById(R.id.frame);

        //FindViewById לטקסט וויו של הקאונטר
        counterTextView = findViewById(R.id.HitCounter);

        ds = new DrawSurfaceView(this);
        frame.addView(ds);
        thread = new Thread(ds);
        thread.start();
    }


    public void pauseResume(View view)
    {
        ds.setIsRunning(false);
    }

    public void playResume(View view)
    {

        ds.setIsRunning(true);
    }
    public void move(View view)
    {
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.moveD();

                // אם הבוליאני בDrawSurfaceView פוזיטיבי, קורא לפונקציה ומחזיר אותו להיות פולס
                if (ds.isuserscore){
                    UpdateScore();
                    ds.isuserscore = false;
                }

            }
        });
        btnU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.moveU();

                // אם הבוליאני בDrawSurfaceView פוזיטיבי, קורא לפונקציה ומחזיר אותו להיות פולס
                if (ds.isuserscore){
                    UpdateScore();
                    ds.isuserscore = false;
                }

            }
        });
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.moveR();

                // אם הבוליאני בDrawSurfaceView פוזיטיבי, קורא לפונקציה ומחזיר אותו להיות פולס
                if (ds.isuserscore){
                    UpdateScore();
                    ds.isuserscore = false;
                }

            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.moveL();

                // אם הבוליאני בDrawSurfaceView פוזיטיבי, קורא לפונקציה ומחזיר אותו להיות פולס
                if (ds.isuserscore){
                    UpdateScore();
                    ds.isuserscore = false;
                }

            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean flag = false;
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            {
                if(y> ds.getY()&&flag == false)
                {
                    ds.moveD();
                    flag = true;
                }
            }
            case MotionEvent.ACTION_MOVE:
            {
                if(x<ds.getX())
                {
                    ds.moveL();
                    flag = true;
                }
                if(x>ds.getX())
                {
                    ds.moveR();
                    flag = true;
                }
            }
            case MotionEvent.ACTION_UP:
            {
                if(y< ds.getY())
                {
                    ds.moveU();
                    flag = true;
                }
            }
        }
        // אם הבוליאני בDrawSurfaceView פוזיטיבי, קורא לפונקציה ומחזיר אותו להיות פולס
        if (ds.isuserscore){
            UpdateScore();
            ds.isuserscore = false;
        }

        return false;
    }


    //הפונקציה מעלה את הקאונטר באחד ומשנה את הטקסט וויו בהתאם
    public void UpdateScore(){
        counter++;
        counterTextView.setText("Points: " + counter);
        if(counter == 10){
            Intent intent = new Intent(GameActivity.this,EndActivity.class);
            startActivity(intent);
        }
    }
}
