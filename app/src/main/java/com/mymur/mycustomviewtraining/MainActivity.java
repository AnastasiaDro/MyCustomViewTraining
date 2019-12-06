package com.mymur.mycustomviewtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Программно создадим макет
        LinearLayout layout = new LinearLayout(this);
        //Сконструируем кастомный View
        CustomView customView = new CustomView(this);
        //Добавим элемент на макет
        Log.d("CustomView", "addView");
        layout.addView(customView);
        //привяжем макет к Активити
        setContentView(layout);

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //setContentView(R.layout.activity_main);
    }
}
