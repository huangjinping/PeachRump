package com.peach.rump.ui.observation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Toast;

import com.peach.rump.R;

/**
 * author Created by harrishuang on 2017/6/22.
 * email : huangjinping@hdfex.com
 */

public class CircleBar extends View {
    private static final String TAG = "CircleBar";

    private RectF mColorWheelRectangle = new RectF();//圆圈的矩形范围
    private Paint mDefaultWheelPaint;////绘制底部灰色圆圈的画笔
    private Paint mColorWheelPaint;//绘制蓝色扇形的画笔
    private Paint textPaint;//中间数值文字的画笔
    private Paint textDesPaint;//描述文字的画笔


    private Paint textMinPaint;//下面的数字
    private Paint textDesMinPaint;//下面的描述


    private float mColorWheelRadius;
    private float circleStrokeWidth;//圆圈的线条粗细
    private float pressExtraStrokeWidth;
    private int mTextColor = getResources().getColor(R.color.blue);//默认文字颜色
    private int mWheelColor = getResources().getColor(R.color.blue);//默认圆环颜色

    private int mMinTextColor = getResources().getColor(R.color.gray);//小描述文字


    private String mText;
    private String mTextDes;//文字的描述

    private String mTextMin;


    private String mTextMinDes;//小的文字描述

    private int mTextDesSize;//描述文字的大小

    private int mMinCount;//小面小数据的动画
    private int mCount;//为了做动画
    private float mSweepAnglePer;//扇形弧度百分比
    private float mSweepAngle;//扇形弧度
    private int mTextSize;//文字大小
    private int mDistance;// 上下文字的距离

    BarAnimation anim;//动画
    private int TIME = 1000;//时间


    public CircleBar(Context context) {
        super(context);
        init(null, 0);
    }

    public CircleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CircleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
        //初始化一些值
        circleStrokeWidth = dip2px(getContext(), 10);
        pressExtraStrokeWidth = dip2px(getContext(), 2);
        mTextSize = dip2px(getContext(), 30);
        mTextDesSize = dip2px(getContext(), 15);
        mDistance = dip2px(getContext(), 20);//文字距离
        //外圆环的画笔
        mColorWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorWheelPaint.setColor(mWheelColor);
        mColorWheelPaint.setStyle(Paint.Style.STROKE);
        mColorWheelPaint.setStrokeWidth(circleStrokeWidth);//圆圈的线条粗细
        mColorWheelPaint.setStrokeCap(Paint.Cap.ROUND);//开启显示边缘为圆形
        //默认圆的画笔
        mDefaultWheelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDefaultWheelPaint.setColor(getResources().getColor(R.color.gray));
        mDefaultWheelPaint.setStyle(Paint.Style.STROKE);
        mDefaultWheelPaint.setStrokeWidth(circleStrokeWidth);//圆圈的线条粗细
        mDefaultWheelPaint.setStrokeCap(Paint.Cap.ROUND);//开启显示边缘为圆形
        //数值的画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textPaint.setColor(mTextColor);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(mTextSize);
        //描述文字的画笔
        textDesPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textDesPaint.setColor(getResources().getColor(R.color.blue));
        textDesPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textDesPaint.setTextSize(mTextDesSize);
        textDesPaint.setTextAlign(Paint.Align.LEFT);


        //小描述文字的画笔
        textMinPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textMinPaint.setColor(getResources().getColor(R.color.alpha_25_black));
        textMinPaint.setTextSize(mTextDesSize);
        textMinPaint.setTextAlign(Paint.Align.LEFT);

        //小描述文字的画笔
        textDesMinPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        textDesMinPaint.setColor(getResources().getColor(R.color.alpha_25_black));
        textDesMinPaint.setTextSize(mTextDesSize);
        textDesMinPaint.setTextAlign(Paint.Align.LEFT);


        mText = "0";
        mTextMin="0";
        mTextDes = "可借额度";
        mTextMinDes = "总额度";


        mSweepAngle = 0;
        anim = new BarAnimation();
        anim.setDuration(TIME);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(mColorWheelRectangle, -225, 270, false, mDefaultWheelPaint);//画外接的圆环
        canvas.drawArc(mColorWheelRectangle, -225, mSweepAnglePer, false, mColorWheelPaint);//画圆环
        Rect bounds = new Rect();
        String textstr = "¥" + mCount + "";


        String textMinStr = "¥" + mMinCount + "";


        textPaint.getTextBounds(textstr, 0, textstr.length(), bounds);
        textDesPaint.getTextBounds(mTextDes, 0, mTextDes.length(), bounds);

        // drawText各个属性的意思(文字,x坐标,y坐标,画笔)
        canvas.drawText(
                textstr + "",
                (mColorWheelRectangle.centerX())
                        - (textPaint.measureText(textstr) / 2),
                mColorWheelRectangle.centerY() + bounds.height() / 2,
                textPaint);

        //描述文字
        canvas.drawText(mTextDes,
                (mColorWheelRectangle.centerX())
                        - (textDesPaint.measureText(mTextDes) / 2),
                mColorWheelRectangle.centerY() + bounds.height() / 2 - 50 - mDistance
                , textDesPaint);

        //小数字
        canvas.drawText(textMinStr,
                (mColorWheelRectangle.centerX())
                        - (textMinPaint.measureText(textMinStr) / 2),
                mColorWheelRectangle.bottom - bounds.height() - 20
                , textMinPaint);
        //小描述

        canvas.drawText(mTextMinDes,
                (mColorWheelRectangle.centerX())
                        - (textDesMinPaint.measureText(mTextMinDes) / 2),
                mColorWheelRectangle.bottom - bounds.height() - 20 - mDistance
                , textDesMinPaint);



        canvas.drawText(textMinStr,
                (mColorWheelRectangle.centerX())
                        - (textMinPaint.measureText(textMinStr) / 2),
                mColorWheelRectangle.top - 20
                , textMinPaint);
//        canvas.drawText(textMinStr,
//                (mColorWheelRectangle.centerX())
//                        - (textMinPaint.measureText(textMinStr) / 2),
//                mColorWheelRectangle.top - 20
//                , textMinPaint);





        drawText(canvas,textMinStr,
                mColorWheelRectangle.left-20,
                mColorWheelRectangle.centerY()-mDistance
                , textMinPaint,-67.5f);




        drawText(canvas,textMinStr,
                mColorWheelRectangle.right-30,
                mColorWheelRectangle.centerY() -mDistance*3-20
                , textMinPaint,67.5f);

    }


    void drawText(Canvas canvas ,String text , float x ,float y,Paint paint ,float angle){
        if(angle != 0){
            canvas.rotate(angle, x, y);
        }
        canvas.drawText(text, x, y, paint);
        if(angle != 0){
            canvas.rotate(-angle, x, y);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        mColorWheelRadius = min - circleStrokeWidth - pressExtraStrokeWidth;

        mColorWheelRectangle.set(circleStrokeWidth + pressExtraStrokeWidth+20, circleStrokeWidth + pressExtraStrokeWidth+20,
                mColorWheelRadius-20, mColorWheelRadius+20);
    }


    @Override
    public void setPressed(boolean pressed) {
        Toast.makeText(getContext(), mText, Toast.LENGTH_SHORT).show();
    }

    public void startCustomAnimation() {
        this.startAnimation(anim);
    }

    public void setText(String text) {
        mText = text;
        this.startAnimation(anim);
    }


    public void setMiniText(String text){
        mTextMin=text;
        this.startAnimation(anim);
    }

    public void setDesText(String text) {
        mTextDes = text;
        this.startAnimation(anim);
    }

    public void setTextColor(int color) {
        mTextColor = color;
        textPaint.setColor(mTextColor);
    }

    public void setSweepAngle(float sweepAngle) {
        mSweepAngle = sweepAngle;
    }

    public void setWheelColor(int color) {
        this.mColorWheelPaint.setColor(color);
    }


    public class BarAnimation extends Animation {
        /**
         * Initializes expand collapse animation, has two types, collapse (1) and expand (0).
         * 1 will collapse view and set to gone
         */
        public BarAnimation() {

        }

        //        * 动画类利用了applyTransformation参数中的interpolatedTime参数(从0到1)的变化特点，
//                * 实现了该View的某个属性随时间改变而改变。原理是在每次系统调用animation的applyTransformation()方法时，
//                * 改变mSweepAnglePer，mCount的值，
//                * 然后调用postInvalidate()不停的绘制view。
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            //mSweepAnglePer，mCount这两个属性只是动画过程中要用到的临时属性，
            //mText和mSweepAngle才是动画结束之后表示扇形弧度和中间数值的真实值。
            if (interpolatedTime < 1.0f) {
                mSweepAnglePer = interpolatedTime * mSweepAngle;
                mCount = (int) (interpolatedTime * Float.parseFloat(mText));

                mMinCount = (int) (interpolatedTime * Float.parseFloat(mTextMin));
            } else {
                mSweepAnglePer = mSweepAngle;
                mMinCount = Integer.parseInt(mTextMin);
                mCount = Integer.parseInt(mText);
            }
            postInvalidate();
        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
