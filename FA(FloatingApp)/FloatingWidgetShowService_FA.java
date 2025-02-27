package com.example.tamesi_floating;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class FloatingWidgetShowService extends Service{

    WindowManager windowManager;
    WindowManager.LayoutParams params ;
    private int x_init_cord, y_init_cord ;
    private final Point szWindow = new Point();
    float height, width;
    long time_start = 0, time_end = 0;
    View floatingView, collapsedView, expandedView;
    ImageView imageClose;


    private boolean isLeft = true;


    public FloatingWidgetShowService() {

    }
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();


        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        floatingView = LayoutInflater.from(this).inflate(R.layout.floating_widget_layout, null);

        params = new WindowManager.LayoutParams( WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER | Gravity.LEFT;
        params.x = 0;
        params.y = 100;


        WindowManager.LayoutParams imageParams = new WindowManager.LayoutParams( 140,
                140,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        imageParams.gravity = Gravity.BOTTOM|Gravity.CENTER;
        imageParams.y = 100;


        imageClose = new ImageView(this);
        imageClose.setImageResource(R.drawable.close);

        imageClose.setVisibility(View.GONE);

        windowManager.addView(imageClose, imageParams);
        windowManager.addView(floatingView, params);

        expandedView = floatingView.findViewById(R.id.Layout_Expended);
        collapsedView = floatingView.findViewById(R.id.Layout_Collapsed);

        height = windowManager.getDefaultDisplay().getHeight();
        width  = windowManager.getDefaultDisplay().getWidth();


        expandedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                collapsedView.setVisibility(View.VISIBLE);
                expandedView.setVisibility(View.GONE);
            }
        });


        floatingView.findViewById(R.id.Widget_expand_Icon).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FloatingWidgetShowService.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                stopSelf();

            }
        });

        floatingView.findViewById(R.id.MainParentRelativeLayout).setOnTouchListener(new View.OnTouchListener() {

            int X_Axis, Y_Axis;
            float TouchX, TouchY;
            boolean inBounded = false; 
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x_cord = (int) event.getRawX();
                int y_cord = (int) event.getRawY();

                int[] imageClose_location = new int[2];
                imageClose.getLocationOnScreen(imageClose_location);
                Log.d("Tag", String.valueOf(imageClose_location[0]));
                Log.d("Tag", String.valueOf(imageClose_location[1]));

                int[] widget_location = new int[2];
                floatingView.getLocationOnScreen(widget_location);
                Log.d("ttt", String.valueOf(widget_location[0]));
                Log.d("ttt", String.valueOf(widget_location[1]));


                switch (event.getAction()) {


                    case MotionEvent.ACTION_DOWN:

                        imageClose.setVisibility(View.VISIBLE);


                        time_start = System.currentTimeMillis();

                        x_init_cord = x_cord;
                        y_init_cord = y_cord;

                        TouchX = event.getRawX();
                        TouchY = event.getRawY();

                        X_Axis = params.x;
                        Y_Axis = params.y;

                        return true;


                    case MotionEvent.ACTION_UP:

                        imageClose.setVisibility(View.GONE);

                        int x_diff = x_cord - x_init_cord;
                        int y_diff = y_cord - y_init_cord;

                        if (Math.abs(x_diff) < 5 && Math.abs(y_diff) < 5) {

                            time_end = System.currentTimeMillis();

                            if ((time_end - time_start) < 300)
                                collapsedView.setVisibility(View.GONE);
                            expandedView.setVisibility(View.VISIBLE);
                        }

                        if (imageClose_location[0] - imageParams.height  <= widget_location[0] && imageClose_location[0] + imageParams.height  >= widget_location[0]
                                && imageClose_location[1] - imageParams.width  <= widget_location[1] && imageClose_location[1] + imageParams.width  >= widget_location[1]){
                            stopSelf();

                        }else {

                            resetPosition(x_cord);
                        }


                        return true;

                    case MotionEvent.ACTION_MOVE:

                        params.x = X_Axis + (int) (event.getRawX() - TouchX);
                        params.y = Y_Axis + (int) (event.getRawY() - TouchY);



                        windowManager.updateViewLayout(floatingView, params);

                        if (imageClose_location[0] - imageParams.height  <= widget_location[0] && imageClose_location[0] + imageParams.height  >= widget_location[0]
                                && imageClose_location[1] - imageParams.width  <= widget_location[1] && imageClose_location[1] + imageParams.width  >= widget_location[1]){

                            imageClose.setImageResource(R.drawable.close);

                        }
                        else {
                            imageClose.setImageResource(R.drawable.close);

                        }

                        return true;
                }
                return false;
            }
        });
    }

    private void resetPosition(int x_cord_now) {
        if (x_cord_now <= width / 2) {
            isLeft = true;
            moveToLeft(x_cord_now);
        } else {
            isLeft = false;
            moveToRight(x_cord_now);
        }

    }

    private void moveToLeft(final int current_x_cord) {
        final int x = (int) (width - current_x_cord);

        new CountDownTimer(0, 0) {

            WindowManager.LayoutParams mParams = (WindowManager.LayoutParams) floatingView.getLayoutParams();

            public void onTick(long t) {
                long step = (500 - t) / 5;

                mParams.x = -(int) (current_x_cord * current_x_cord * step);

                windowManager.updateViewLayout(floatingView, mParams);
            }

            public void onFinish() {
                mParams.x = 0;

                windowManager.updateViewLayout(floatingView, mParams);
            }
        }.start();
    }

    private void moveToRight(final int current_x_cord) {

        new CountDownTimer(0, 0) {

            WindowManager.LayoutParams mParams = (WindowManager.LayoutParams) floatingView.getLayoutParams();

            public void onTick(long t) {
                long step = (500 - t) / 5;

                mParams.x = (int) (width + (current_x_cord * current_x_cord * step) - floatingView.getWidth());

                windowManager.updateViewLayout(floatingView, mParams);
            }

            public void onFinish() {
                mParams.x = (int) (width- floatingView.getWidth());

                windowManager.updateViewLayout(floatingView, mParams);
            }
        }.start();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();

        if(floatingView != null){
            windowManager.removeView(floatingView);

        }
        if (imageClose != null){

            windowManager.removeView(imageClose);
        }

    }}
