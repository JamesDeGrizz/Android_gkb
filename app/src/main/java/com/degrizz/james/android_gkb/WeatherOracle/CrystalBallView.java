package com.degrizz.james.android_gkb.WeatherOracle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CrystalBallView extends View {
    private int ballColor = Color.BLACK;
    private int handsColor = Color.BLACK;
    private int textColor = Color.BLACK;
    private int textSize = 60;
    private int strokeWidth;

    private Paint ballPaint;
    private Paint handsPaint;
    private Paint textPaint;

    private String text = "?";

    int width;
    int height;
    int centerX;
    int centerY;
    float circleRadius;

    RectF oval1;
    RectF oval2;
    RectF oval3;
    RectF oval4;

    public CrystalBallView(Context context) {
        super(context);
        init();
    }

    public CrystalBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    public CrystalBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    public void setText(String text) {
        this.text = text;
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CrystalBallView, 0, 0);
        ballColor = typedArray.getColor(R.styleable.CrystalBallView_ball_color, Color.BLACK);
        handsColor = typedArray.getColor(R.styleable.CrystalBallView_hands_color, Color.BLACK);
        textColor = typedArray.getColor(R.styleable.CrystalBallView_text_color, Color.BLACK);
        textSize = typedArray.getInt(R.styleable.CrystalBallView_text_size, 80);
        strokeWidth = typedArray.getInt(R.styleable.CrystalBallView_stroke_width, 10);
        typedArray.recycle();
    }

    private void init() {
        ballPaint = new Paint();
        ballPaint.setColor(ballColor);
        ballPaint.setStrokeWidth(strokeWidth);
        ballPaint.setStyle(Paint.Style.STROKE);

        handsPaint = new Paint();
        handsPaint.setColor(handsColor);
        handsPaint.setStrokeWidth(strokeWidth);
        handsPaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingTop() - getPaddingBottom();
        centerX = width / 2;
        centerY = height / 2;
        circleRadius = centerX * 0.8f;

        float percentage = width * 0.05f;
        oval1 = new RectF(centerX - circleRadius + percentage
                , centerY - circleRadius + percentage
                , centerX + circleRadius - percentage
                , centerY + circleRadius - percentage);

        percentage = width * 0.1f;
        oval2 = new RectF(centerX - circleRadius + percentage
                , centerY - circleRadius + percentage
                , centerX + circleRadius - percentage
                , centerY + circleRadius - percentage);

        percentage = width * 0.15f;
        oval3 = new RectF(centerX - circleRadius + percentage
                , centerY - circleRadius + percentage
                , centerX + circleRadius - percentage
                , centerY + circleRadius - percentage);

        percentage = width * 0.07f;
        oval4 = new RectF(centerX - circleRadius - percentage
                , centerY - circleRadius - percentage
                , centerX + circleRadius + percentage
                , centerY + circleRadius + percentage);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(centerX
                , centerY
                , circleRadius
                , ballPaint);

        canvas.drawArc(oval1, 280, 70, false, ballPaint);
        canvas.drawArc(oval2, 290, 50, false, ballPaint);
        canvas.drawArc(oval3, 300, 30, false, ballPaint);

        canvas.drawText(text, centerX - textSize - 5, centerY + textSize / 2, textPaint);

        canvas.drawLine((float)getPaddingLeft(), (float)centerY + circleRadius,
                (float)centerX - circleRadius * 0.6f, (float)centerY + circleRadius,
                handsPaint);

        canvas.drawLine((float)(width - getPaddingRight()), (float)centerY - circleRadius,
                (float)centerX + circleRadius * 0.6f, (float)centerY - circleRadius,
                handsPaint);

        canvas.drawArc(oval4, 230, 72, false, handsPaint);
        canvas.drawArc(oval4, 50, 72, false, handsPaint);
    }
}
