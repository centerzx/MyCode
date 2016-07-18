package com.center.mycode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageButton;

/**
 * 自定义SwitchButton(仿Android 5.0自带)
 *
 * @author HeyGays
 */
public class SwitchButton extends ImageButton {

    private final int ROUND_MARGIN = 2;// 圆点按钮的外边距dp，避免圆点边缘被遮盖

    private Paint paint;// 画圆点的笔
    private boolean isOn;// 开关状态，左关右开
    private float touchX = 0;// 手指按下的x坐标
    private int touchSlop;// 最小滑动距离
    private int backHeight = 24;// 背景默认高度dp
    private int backWidth;// 背景宽度dp
    private int backPadding = 5;// 背景的默认padding dp
    private int backColor_Off = Color.GRAY;// 背景的默认填充颜色
    private int backColor_On = Color.DKGRAY;// 背景的默认填充颜色
    private int backRoundRadius = backHeight;// 背景的默认圆角半径dp
    private int m = 0;// 相对于最左的位移距离 dp
    private int m_Max;// m的最大坐标
    private int round_Size;// 圆点的大小
    private GradientDrawable gd;// 背景drawble
    private RectF roundBtnRect = new RectF();// 圆点所在矩形范围

    private OnSwithChanged onSwithChanged;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }


    public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSize();
        initBack();
        initPaint();
    }

    /**
     * 初始化一些尺寸
     */
    private void initSize() {
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        // 根据高度计算圆点大小
        round_Size = backHeight - 2 * ROUND_MARGIN;
        // 根据圆点大小计算开关宽度
        backWidth = 2 * (round_Size + ROUND_MARGIN);
        // 根据宽度计算最大位移坐标
        m_Max = backWidth - ROUND_MARGIN - round_Size - 1;
    }

    /**
     * 初始化背景
     */
    private void initBack() {
        int padding = dp2px(backPadding);
        gd = new GradientDrawable();
        gd.setColor(backColor_Off);
        gd.setCornerRadius(dp2px(backRoundRadius));
        if (padding == 0) {// 防止黑边
            gd.setStroke(padding, Color.TRANSPARENT);
        }
        setPadding(padding, padding, padding, padding);
        setImageDrawable(gd);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);// 关闭硬件加速
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
        paint.setDither(true);
        paint.setShadowLayer(1, 0, 1, Color.DKGRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(dp2px(backWidth), dp2px(backHeight));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        roundBtnRect.left = dp2px(ROUND_MARGIN + m);
        roundBtnRect.top = dp2px(ROUND_MARGIN);
        roundBtnRect.right = roundBtnRect.left + dp2px(round_Size);
        roundBtnRect.bottom = roundBtnRect.top + dp2px(round_Size);
        canvas.drawArc(roundBtnRect, 0, 360, false, paint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                toChange();
                break;
        }
        return true;
    }

    /**
     * 改变状态
     */
    private void toChange() {
        if (!isOn) {
            m = m_Max;
            isOn = true;
            gd.setColor(backColor_On);
            setImageDrawable(gd);
            if (onSwithChanged != null) {
                onSwithChanged.opened(this);
            }
        } else {
            m = 0;
            isOn = false;
            gd.setColor(backColor_Off);
            setImageDrawable(gd);
            if (onSwithChanged != null) {
                onSwithChanged.colsed(this);
            }
        }
        postInvalidate();
    }

    /**
     * dip转为 px
     */
    public int dp2px(int dipValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 设置圆点的颜色
     *
     * @param roundBtnColor
     */
    public void setRoundBtnColor(int roundBtnColor) {
        paint.setColor(roundBtnColor);
        invalidate();
    }

    /**
     * 设置背景高度超过50会变形
     *
     * @param backHeight
     */
    public void setBackHeight(int backHeight) {
        this.backHeight = backHeight < this.backHeight ? this.backHeight : backHeight;
        initSize();
    }

    /**
     * 设置背景padding
     *
     * @param backPadding
     */
    public void setBackPadding(int backPadding) {
        if (backPadding <= 0) {
            backPadding = 0;
        }
        if (backPadding > backHeight) {
            backPadding = this.backPadding;
        }
        this.backPadding = backPadding;
        initBack();
    }

    /**
     * 设置关闭的背景颜色
     *
     * @parambackColor_On
     */
    public void setBackColorWithOff(int backColor_Off) {
        this.backColor_Off = backColor_Off;
        initBack();
    }

    /**
     * 设置打开的背景颜色
     *
     * @param backColor_On
     */
    public void setBackColorWithOn(int backColor_On) {
        this.backColor_On = backColor_On;
        initBack();
    }

    /**
     * 返回开关状态
     *
     * @return
     */
    public boolean isOn() {
        return isOn;
    }

    public void setOnSwithChanged(OnSwithChanged onSwithChanged) {
        this.onSwithChanged = onSwithChanged;
    }

    public interface OnSwithChanged {
        void opened(SwitchButton switchButton);

        void colsed(SwitchButton switchButton);
    }
}
