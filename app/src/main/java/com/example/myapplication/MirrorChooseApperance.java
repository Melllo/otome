package com.example.myapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
public class MirrorChooseApperance extends Activity {
    Button save_and_go;
    TextView textView;
    RadioGroup radiogroup;
    static int apperance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mirror_chooseapperance_layout);
        save_and_go = findViewById(R.id.saveandgo);
        textView = findViewById(R.id.mirror_text);
        radiogroup = findViewById(R.id.radioGroup1);

        RadioButton redRadioButton = (RadioButton)findViewById(R.id.radio0);
        redRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton greenRadioButton = (RadioButton)findViewById(R.id.radio1);
        greenRadioButton.setOnClickListener(radioButtonClickListener);

        RadioButton blueRadioButton = (RadioButton)findViewById(R.id.radio2);
        blueRadioButton.setOnClickListener(radioButtonClickListener);

        getWindow().setBackgroundDrawableResource(R.drawable.bathroom);
        save_and_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "You choosed girl #"+(apperance+1), Toast.LENGTH_SHORT);
                toast.show();
                MirrorChooseApperance.this.finish();
            }
        });

    }
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.radio0: apperance=0;
                    break;
                case R.id.radio1:  apperance=1;
                    break;
                case R.id.radio2:  apperance=2;
                    break;
                default:
                    break;
            }
        }
    };
}