package com.pedrogomez00057616.pdm.alarmprogrammer;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    NumberPicker numberPickerHour;
    NumberPicker numberPickerMinutes;
    Spinner spinnerAMPM;
    Button buttonSetAlarm;
    EditText editTextAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //getting numberPickers objects
        numberPickerHour=findViewById(R.id.numberPickerHour);
        numberPickerMinutes=findViewById(R.id.numberPickerMinutes);
        //settgin max and min numberpicker
        setMinAndMax(numberPickerHour, numberPickerMinutes);
        //getting spinner object
        spinnerAMPM=findViewById(R.id.spinnerAMPM);
        //getting edittext object
        editTextAlarm=findViewById(R.id.editTextAlarm);
        //setting action listener to button
        buttonSetAlarm=findViewById(R.id.buttonSetAlarm);

        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlarm(editTextAlarm.getText().toString(),numberPickerHour.getValue()+settingHour(spinnerAMPM, numberPickerHour.getValue()), numberPickerMinutes.getValue());
            }
        });


    }

    //Setting min and max values
    private void setMinAndMax(NumberPicker nph, NumberPicker npm){
        nph.setMinValue(0);
        nph.setMaxValue(12);
        npm.setMinValue(0);
        npm.setMaxValue(59);
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //Tomando en cuenta el AM y PM, si es PM diferente a 12 se le sumara 12, ya que el reloj trabja en formato de 24 horas
    public int settingHour(Spinner spinAMPM, int hour){

        if(spinAMPM.getSelectedItem().toString().contains("PM")&&hour!=12){
            return 12;
        }else{
            return 0;
        }
    }
}
