/*
 * Copyright (C) 2017 lsjwzh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lsjwzh.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 分段进度条
 */
public class StepProgressBar extends View {
  public static final int DEFAULT_STEP_COLOR = Color.WHITE;
  public static final int DEFAULT_STEP_COUNT = 10;
  private int mStepColor;
  private Paint mStepPaint;
  private int mMaxStep = DEFAULT_STEP_COUNT;
  private int mCurrentStep;
  private int mStepSpaceWidth = (int) dp2px(1.5f);

  public StepProgressBar(Context context) {
    this(context, null);
  }

  public StepProgressBar(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public StepProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StepProgressBar, defStyleAttr, 0);
    mStepColor = ta.getColor(R.styleable.StepProgressBar_spb_step_color, DEFAULT_STEP_COLOR);
    mStepSpaceWidth = ta.getDimensionPixelSize(R.styleable.StepProgressBar_spb_step_space_width, mStepSpaceWidth);
    mMaxStep = ta.getInt(R.styleable.StepProgressBar_spb_max_step_count, DEFAULT_STEP_COUNT);
    ta.recycle();

    initializePaints();
  }

  private void initializePaints() {
    mStepPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mStepPaint.setColor(mStepColor);
  }

  private float dp2px(float dp) {
    final float scale = getResources().getDisplayMetrics().density;
    return dp * scale + 0.5f;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
  }

  private int measure(int measureSpec, boolean isWidth) {
    int result;
    int mode = MeasureSpec.getMode(measureSpec);
    int size = MeasureSpec.getSize(measureSpec);
    int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
    if (mode == MeasureSpec.EXACTLY) {
      result = size;
    } else {
      result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
      result += padding;
      if (mode == MeasureSpec.AT_MOST) {
        if (isWidth) {
          result = Math.max(result, size);
        } else {
          result = Math.min(result, size);
        }
      }
    }
    return result;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawStep(canvas);
  }

  private void drawStep(Canvas canvas) {
    float widthCanDraw = getWidth() - getPaddingLeft() - getPaddingRight();
    float stepItemWidth = (widthCanDraw - mStepSpaceWidth * (mMaxStep - 1)) / mMaxStep;
    //初始化分段符位置
    RectF rectF = new RectF();
    rectF.left = getPaddingLeft();
    rectF.top = getPaddingTop();
    rectF.right = rectF.left + mStepSpaceWidth;
    rectF.bottom = getHeight() - getPaddingBottom();
    //绘制分段间隔
    for (int i = 0; i < mCurrentStep; i++) {
      rectF.left = getPaddingLeft() + stepItemWidth * i + mStepSpaceWidth * i;
      rectF.right = rectF.left + stepItemWidth;
      canvas.drawRect(rectF, mStepPaint);
    }
  }

  public void setProgressStep(int progress) {
    if (mCurrentStep != progress) {
      mCurrentStep = progress;
      invalidate();
    }
  }

  public void setMaxStep(int stepCount) {
    mMaxStep = stepCount;
    invalidate();
  }

  public void setStepSpaceWidth(int stepSpaceWidth) {
    mStepSpaceWidth = stepSpaceWidth;
    invalidate();
  }

  public void setStepColor(int stepColor) {
    this.mStepColor = stepColor;
  }
}
