package com.mymur.mycustomviewtraining;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
//поля
    //тек для логов
    private final static String TAG = "CustomView";
    //это наши краски, с помощью пэинта можем нарисовать круг и покрасить
    private Paint paint;
    //радиус круга
    private int radius = 100;
    //цвет нашего круга
    private int color = Color.BLACK;

    //нажат ли круг,нужно, чтобы делать анимацию
    private boolean pressed = false;
    //в него будем сеттить поле извне, чтобы могли обработать нажатие и дорисовать ещё анимацию
    //см метод onTouchEvent - мы в нем сперва делаем анимацию,а потом вызываем onClick, если мы свой он клик не переопределим, он будет срабатывать ДО анимации
    private View.OnClickListener listener;


    //необходимо переопределить конструкторы View обычно 3 из 4х
    //этот чтобы просто программно отрисовать
    public CustomView(Context context) {
        super(context);
        initView();
    }

    //Этот - чтобы мочь добавить view в Layout
    //attrs - массив атрибутов, необходимый для добавления в Layout
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        initView();
    }


    //Это чтобы ещё стиль поместить
    //int - ссылка на стиль
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initView();
    }


    private void initView() {
        //здесь создаем краски, должны вызвать этот метод в каждом конструкторе, который у нас есть
        Log.d(TAG, "Constructor");
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }

    // чтобы добавлять View в Layout, нужно инициализировать атрибуты
    //"обработка параметров в xml"
    //атрибуты для своей View определяются в отдельном xml-файле ресурсы->values->создаём файл attr
    private void  initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
        setRadius(typedArray.getInt(R.styleable.CustomView_cv_Radius, 50));
        //BLUE - цвет по умолчанию, если в атрибутах не указывается цвет
        setColor(typedArray.getColor(R.styleable.CustomView_cv_Color, Color.BLUE));
        typedArray.recycle();
    }

    public void setRadius (int radius) {
        this.radius = radius;
    }

    public void setColor(int color){
        this.color = color;
    }

    //чтобы нарисовать View, нужно переопределить метод onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        super.onDraw(canvas);
        if(pressed) {
            canvas.drawCircle(radius, radius, radius/10, paint);
        } else {
            canvas.drawCircle(radius, radius, radius, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN) {//Нажали
            pressed = true;
            invalidate();    //перерисовка элемента
            if (listener != null) listener.onClick(this);
        } else if(action == MotionEvent.ACTION_UP) {//Отпустили
            pressed = false;
            invalidate();    //перерисовка элемента
        }
        return  true;
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {listener = l;}

}
