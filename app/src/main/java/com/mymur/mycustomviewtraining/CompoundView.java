package com.mymur.mycustomviewtraining;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class CompoundView extends RelativeLayout {
    private EditText phoneInput, smsInput;
    private Button callBtn, sendSmsBtn;


    public CompoundView(Context context) {
        super(context);
        initViews(context);
    }

    public CompoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public CompoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.card_compound_view, this);
    }


    //Вся магия с нашим View происходит уже в методе onFinishInflate
    //то есть здесь мы уже можем найти наши view на Layout-е
    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        initUI();
        //дальше тут идёт вся основная логика
        // set onClickListeners
        //other data logics
    }


    //нашли наши View
    private void initUI(){
        phoneInput = this.findViewById(R.id.telephoneInput);
        smsInput = this.findViewById(R.id.smsInput);
        callBtn = this.findViewById(R.id.callBtn);
        sendSmsBtn = this.findViewById(R.id.sendSms);
    }
}
