package com.mymur.mycustomviewtraining;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.net.URI;

import static android.util.Patterns.PHONE;

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
        setOnCallBtnClickBehavior();
        setOnSmsBtnClickBehavior();
    }


    //нашли наши View
    private void initUI(){
        phoneInput = this.findViewById(R.id.telephoneInput);
        smsInput = this.findViewById(R.id.smsInput);
        callBtn = this.findViewById(R.id.callBtn);
        sendSmsBtn = this.findViewById(R.id.sendSms);
    }


    private void setOnCallBtnClickBehavior() {
        callBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneInput.getText().toString().replaceAll("-", "");
                //проверим телефон
                //android.util.Patterns.PHONE.matcher(phoneNumber).matches()
                //т.е. у нас инпут тАйп у поля  - Phone, то проверка нам не нужна
//                if (PHONE.matcher(phoneNumber).matches()) {
//                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    getContext().startActivity(intent);
//                }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);
            }
        });
    }

    private void setOnSmsBtnClickBehavior() {
        sendSmsBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsText = smsInput.getText().toString();
                String phoneNumber = phoneInput.getText().toString().replaceAll("-", "");
                String toNumberSms="smsto:" + phoneNumber;

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(toNumberSms));
                intent.putExtra("sms_body", smsText);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

                //либо сразу отправка:
                //но этот способ требует в манифесте указать uses pernmission
                //в манифесте перед app
                //<uses-permission android:name="android.permission.SEND_SMS"/>
              //  SmsManager.getDefault().sendTextMessage(phoneNumber, null, smsText, null, null);


            }
        });
    }


}
