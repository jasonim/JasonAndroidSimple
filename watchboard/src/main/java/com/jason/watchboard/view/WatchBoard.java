package com.jason.watchboard.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.jason.watchboard.R;
import com.jason.watchboard.utils.SizeUtil;

import java.util.Calendar;

/**
 * Created by hujd on 16-11-2.
 */
public class WatchBoard extends View {
	private float mRaius; //外圆
	private float mPadding; //边距
	private float mTextSize;
	private float mHourPointWidth;
	private float mMinutePointWidth;
	private float mSecondPointWidth;
	private int  mPointRadius;
	private float mPointEndLength; //指针末尾长度
	private int mColorLong; //长线的颜色
	private int mColorShort; //短线的颜色
	private int mHourPointColor;
	private int mMinutePointColor;
	private Paint mPaint;
	private int mSecondPointColor;

	public WatchBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		obtainStyledAttrs(attrs);
		initPaint();
	}

	private void obtainStyledAttrs(AttributeSet attrs) {
		TypedArray array = null;
		try {
			array = getContext().obtainStyledAttributes(attrs, R.styleable.WatchBoard);
			mPadding = array.getDimension(R.styleable.WatchBoard_wb_padding, DptoPx(10));
			mTextSize = array.getDimension(R.styleable.WatchBoard_wb_text_size, SptoPx(16));
			mHourPointWidth = array.getDimension(R.styleable.WatchBoard_wb_hour_pointer_width, DptoPx(5));
			mMinutePointWidth = array.getDimension(R.styleable.WatchBoard_wb_minute_pointer_width, DptoPx(3));
			mSecondPointWidth = array.getDimension(R.styleable.WatchBoard_wb_second_pointer_width, DptoPx(2));
			mPointRadius = (int) array.getDimension(R.styleable.WatchBoard_wb_pointer_corner_radius, DptoPx(10));
			mPointEndLength = array.getDimension(R.styleable.WatchBoard_wb_pointer_end_length, DptoPx(10));

			mColorLong = array.getColor(R.styleable.WatchBoard_wb_scale_long_color, Color.argb(225, 0, 0, 0));
			mColorShort = array.getColor(R.styleable.WatchBoard_wb_scale_short_color, Color.argb(125, 0, 0, 0));
			mHourPointColor = array.getColor(R.styleable.WatchBoard_wb_hour_pointer_color, Color.BLACK);
			mMinutePointColor = array.getColor(R.styleable.WatchBoard_wb_minute_pointer_color, Color.BLACK);
			mSecondPointColor = array.getColor(R.styleable.WatchBoard_wb_second_pointer_color, Color.RED);
		} catch (Exception e) {
			mPadding = DptoPx(10);
			mTextSize = SptoPx(16);
			mHourPointWidth = DptoPx(5);
			mMinutePointWidth = DptoPx(3);
			mSecondPointWidth = DptoPx(2);
			mPointRadius = (int) DptoPx(10);
			mPointEndLength = DptoPx(10);

			mColorLong = Color.argb(225, 0, 0, 0);
			mColorShort = Color.argb(125, 0, 0, 0);
			mMinutePointColor = Color.BLACK;
			mSecondPointColor = Color.RED;
		} finally {
			if (array != null) {
				array.recycle();
			}
		}
	}

	private float SptoPx(int value) {
		return SizeUtil.Sp2Px(getContext(), value);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = 1000; //设置一个最小值
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode == MeasureSpec.AT_MOST
				|| widthMode == MeasureSpec.UNSPECIFIED
				|| heightMode == MeasureSpec.AT_MOST
				|| heightMode == MeasureSpec.UNSPECIFIED) {
			try {
				throw new NoDetermineSizeException("宽度高度至少有一个确定的值，不能同时为wrap_content");
			} catch (NoDetermineSizeException e) {
				e.printStackTrace();
			}
		} else {
			if (widthMode == MeasureSpec.EXACTLY) {
				width = Math.min(width, widthSize);
			}
			if (heightMode == MeasureSpec.EXACTLY) {
				width = Math.min(heightSize, width);
			}
		}

		setMeasuredDimension(width, width);
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mRaius = (Math.min(w, h) - mPadding) / 2;
		mPointEndLength = mRaius / 6;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		canvas.translate(getWidth() / 2, getHeight() / 2);
		//绘制外语背景
		paintCircle(canvas);

		paintScale(canvas);

		paintPoints(canvas);

		canvas.restore();
		postInvalidateDelayed(1000);
	}

	//绘制指针
	private void paintPoints(Canvas canvas) {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY); //时
		int minute = calendar.get(Calendar.MINUTE); //分
		int second = calendar.get(Calendar.SECOND); //秒
		int angleHour = (hour % 12) * 360 / 12; //时针转过的角度
		int angleMinute = minute * 360 / 60; //分针转过的角度
		int angleSecond = second * 360 / 60; //秒针转过的角度
		//绘制时针
		paintHourPoints(canvas, angleHour);

		//绘制分针
		paintMinutePoints(canvas, angleMinute);
		//绘制秒针
		paintSecondPoints(canvas, angleSecond);

		//绘制中心小圆
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(mSecondPointColor);
		canvas.drawCircle(0, 0, mSecondPointWidth * 4, mPaint);
	}

	private void paintSecondPoints(Canvas canvas, int angleSecond) {
		canvas.save();
		canvas.rotate(angleSecond);
		RectF rectFSecond = new RectF(-mSecondPointWidth / 2, -mRaius + 15, mSecondPointWidth / 2, mPointEndLength);
		mPaint.setColor(mSecondPointColor);
		mPaint.setStrokeWidth(mSecondPointWidth);
		canvas.drawRoundRect(rectFSecond, mPointRadius, mPointRadius, mPaint);
		canvas.restore();
	}

	private void paintMinutePoints(Canvas canvas, int angleMinute) {
		canvas.save();
		canvas.rotate(angleMinute);
		RectF rectFMinute = new RectF(-mMinutePointWidth / 2, -mRaius * 3.5f / 5, mMinutePointWidth / 2, mPointEndLength);
		mPaint.setColor(mMinutePointColor);
		mPaint.setStrokeWidth(mMinutePointWidth);
		canvas.drawRoundRect(rectFMinute, mPointRadius, mPointRadius, mPaint);
		canvas.restore();
	}

	private void paintHourPoints(Canvas canvas, int angleHour) {
		canvas.save();
		canvas.rotate(angleHour); //旋转到时针的角度
		RectF rectFHour = new RectF(-mHourPointWidth / 2, -mRaius * 3 / 5, mHourPointWidth / 2, mPointEndLength);
		mPaint.setColor(mHourPointColor); //设置指针颜色
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(mHourPointWidth); //设置边界宽度
		canvas.drawRoundRect(rectFHour, mPointRadius, mPointRadius, mPaint); //绘制时针
		canvas.restore();
	}

	//绘制刻度
	private void paintScale(Canvas canvas) {
		mPaint.setStrokeWidth(SizeUtil.Dp2Px(getContext(), 1));
		int lineWidth = 0;
		for (int i = 0; i < 60; i++) {
			if (i % 5 == 0) {//整点
				mPaint.setStrokeWidth(SizeUtil.Dp2Px(getContext(), 1.5f));
				mPaint.setColor(mColorLong);
				lineWidth = 40;
				paintText(canvas, lineWidth, i);
			} else {
				mPaint.setStrokeWidth(SizeUtil.Dp2Px(getContext(), 1));
				mPaint.setColor(mColorShort);
				lineWidth = 30;
			}
			float startY = -mRaius + mPadding;
			canvas.drawLine(0, startY, 0, startY + lineWidth, mPaint);
			canvas.rotate(6);
		}
	}

	private void paintText(Canvas canvas, int lineWidth, int i) {
		mPaint.setTextSize(mTextSize);
		String text = ((i / 5) == 0 ? 12 : (i / 5)) + "";
		Rect textBound = new Rect();
		mPaint.getTextBounds(text, 0, text.length(), textBound);
		mPaint.setColor(Color.BLACK);
		canvas.save();
		canvas.translate(0, -mRaius + DptoPx(5) + lineWidth + mPadding + (textBound.bottom - textBound.top) / 2);
		mPaint.setStyle(Paint.Style.FILL);
		canvas.rotate(-6 * i);
		canvas.drawText(text, -(textBound.right + textBound.left) / 2, -(textBound.bottom + textBound.top) / 2, mPaint);
		canvas.restore();
	}

	private void paintCircle(Canvas canvas) {
		mPaint.setColor(Color.WHITE);
		mPaint.setStyle(Paint.Style.FILL);
		canvas.drawCircle(0, 0, mRaius, mPaint);
	}


	private float DptoPx(int value) {
		return SizeUtil.Dp2Px(getContext(), value);
	}


}
