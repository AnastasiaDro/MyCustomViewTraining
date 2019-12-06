package com.mymur.mycustomviewtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class SensorsActivity extends AppCompatActivity {
    private TextView textConsole;
    private TextView textLight;
    private SensorManager sensorManager;
    private List<Sensor> sensors;
    private Sensor sensorLight;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);
        initViews();
        getSensors();
        showSensors();
    }

    private void initViews() {
        textConsole = findViewById(R.id.textConsole);
        textLight = findViewById(R.id.textLight);
    }

    private void getSensors() {
        //Менеджер датчиков
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //получить все датчики, какие есть
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        //датчик освещённости (он есть на многих моделях)
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //регистрируем слушатель датчика освещённости
        sensorManager.registerListener(listenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //Если приложение свёрнуто, не будем тратить энергию на получение информации по датчикам
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerLight, sensorLight);
    }

    //Вывод всех сенсоров
    private void showSensors() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Sensor sensor : sensors) {
            stringBuilder.append("name = ").append(sensor.getName())
                    .append(", type = ").append(sensor.getType())
                    .append("\n")
                    //venfor - разработчик сенсора
                    .append("vendor = ").append(sensor.getVendor())
                    .append(" ,version = ").append(sensor.getVersion())
                    .append("\n")
                    .append("max = ").append(sensor.getMaximumRange())
                    //resolution - состояние сенсора
                    .append(", resolution = ").append(sensor.getResolution())
                    .append("\n").append("---------------------------------------").append("\n");
        }
        textConsole.setText(stringBuilder);
    }



    //Вывод датчика освещённости
    private void showLightSensors(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Light Sensor value = ").append(event.values[0])
                .append("\n").append("===============================").append("\n");
        textLight.setText(stringBuilder);
    }



    //Слушатель датчика освещённости
    SensorEventListener listenerLight = new SensorEventListener() {

        @Override
        public void onAccuracyChanged (Sensor sensor, int accurancy) {
            //когда изменяется точность измерений, мы ничего не делаем
            //тут можем написать, что с вашим сенсором что-то не то
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
        showLightSensors(event);
        }

    };

}
