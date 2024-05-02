package com.example.kidwise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomClockView extends View {

    private int hours;
    private int minutes;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomClockView(Context context) {
        super(context);
    }

    public CustomClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomClockView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2 - 20; // Padding of 20px
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw the clock face
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(centerX, centerY, radius, paint);

        // Draw hour markers
        paint.setStrokeWidth(8);
        for (int i = 0; i < 12; i++) {
            double angle = Math.PI / 6 * i - Math.PI / 2;
            int lineLength = 30; // Length of the hour markers
            canvas.drawLine(
                    centerX + (int) (radius * 0.9 * Math.cos(angle)),
                    centerY + (int) (radius * 0.9 * Math.sin(angle)),
                    centerX + (int) (radius * Math.cos(angle)),
                    centerY + (int) (radius * Math.sin(angle)),
                    paint
            );
        }

        // Draw minute markers
        paint.setStrokeWidth(3);
        for (int i = 0; i < 60; i++) {
            if (i % 5 != 0) { // Skip hour markers
                double angle = Math.PI / 30 * i - Math.PI / 2;
                int lineLength = 15; // Length of the minute markers
                canvas.drawLine(
                        centerX + (int) (radius * 0.95 * Math.cos(angle)),
                        centerY + (int) (radius * 0.95 * Math.sin(angle)),
                        centerX + (int) (radius * Math.cos(angle)),
                        centerY + (int) (radius * Math.sin(angle)),
                        paint
                );
            }
        }

        // Draw the hour hand
        paint.setColor(Color.RED);
        paint.setStrokeWidth(8);
        double hourAngle = Math.PI / 6 * (hours + minutes / 60.0) - Math.PI / 2;
        canvas.drawLine(centerX, centerY,
                centerX + (int) (radius * 0.5 * Math.cos(hourAngle)),
                centerY + (int) (radius * 0.5 * Math.sin(hourAngle)),
                paint);

        // Draw the minute hand
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        double minuteAngle = Math.PI / 30 * minutes - Math.PI / 2;
        canvas.drawLine(centerX, centerY,
                centerX + (int) (radius * 0.8 * Math.cos(minuteAngle)),
                centerY + (int) (radius * 0.8 * Math.sin(minuteAngle)),
                paint);
    }

    public void setTime(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        invalidate(); // Redraw the clock
    }
}
