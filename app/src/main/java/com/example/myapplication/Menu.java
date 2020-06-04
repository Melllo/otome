package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import static com.example.myapplication.R.id;
import static com.example.myapplication.R.layout;

public class Menu extends Activity {
    Button start;
    Button exit;
    MainActivity mainActivity;

    private static final String TAG = "count";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        mainActivity = new MainActivity();
        start = findViewById(R.id.start);
        exit = findViewById(id.menu_exit);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu.this.finish();
            }
        });
    }


}

