package com.example.fixedastroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;


public class DrawSurfaceView extends SurfaceView implements Runnable {
    int interval=5;
    float dx=10;
    float dy=10;
    Context context;
    SurfaceHolder holder;
    boolean threadRunning = true;
    boolean isRunning = true;
    boolean isuserscore = false;
    Bitmap bitmap;
    float x =100;
    float y =100;
    int counter = 0;
    TextView counttext;
    public DrawSurfaceView(Context context)
    {
        super(context);
        this.context = context;
        holder = getHolder();
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.astro4);
    }
    public void setIsRunning(boolean flag)
    {
        this.isRunning=flag;
    }
    public  float getX()
    {
        return this.x;
    }
    public  float getY()
    {
        return this.y;
    }

    @Override
    public void run() {
        while (threadRunning)
        {

            if(isRunning)
            {
                if(!holder.getSurface().isValid())
                    continue;

                Canvas c = null;
                try
                {
                    c = this.getHolder().lockCanvas();//what with line meaning?
                    synchronized (this.getHolder()){
                        c.drawRGB(100,100,255);//Try pushing this line into a remark. what happened? you can change the color.
                        c.drawBitmap(bitmap,x,y,null);
                        automaticMove();
                    }
                    Thread.sleep(interval);
                }
                catch (Exception e)
                {

                }
                finally {
                    if(c!=null)
                    {
                        this.getHolder().unlockCanvasAndPost(c);//what this line meaning?
                    }
                }
            }
        }
    }
   /* public void automaticMove()
    {
        x = x + dx;
        y = y + dy;
        if(x < 0 || x > this.getWidth())
            dx = -dx;
        if(y < 0 || y > this.getHeight())
            dy = -dy;
    }
    */

    public void automaticMove() {
        x = x + dx;
        y = y + dy;



        // Check if the bitmap has touched the left or right edge of the screen
        if (x < 0 || x + bitmap.getWidth() > this.getWidth()) {
            dx = -dx;
        }

        // Check if the bitmap has touched the top or bottom edge of the screen
        if (y < 0 || y + bitmap.getHeight() > this.getHeight()) {
            dy = -dy;
        }

        // Check if the bitmap is touching the top-left corner of the screen
        if (x < 50 && y < 50) {
            isuserscore = true;
        }

        // Check if the bitmap is touching the top-right corner of the screen
        if (x + bitmap.getWidth() > this.getWidth() - 50 && y < 50) {
            isuserscore = true;
        }

        // Check if the bitmap is touching the bottom-left corner of the screen
        if (x < 50 && y + bitmap.getHeight() > this.getHeight() - 50) {
            isuserscore = true;
        }

        // Check if the bitmap is touching the bottom-right corner of the screen
        if (x + bitmap.getWidth() > this.getWidth() - 50 && y + bitmap.getHeight() > this.getHeight() - 50) {
            isuserscore = true;
        }


    }





    public void moveD()
    {
        dy=Math.abs(dy);
    }
    public void moveR()
    {
        dx=Math.abs(dx);
    }
    public void moveL()
    {
        dx=-Math.abs(dx);
    }
    public void moveU()
    {
        dy=-Math.abs(dy);
    }
}