package com.example.myapplication;


import android.content.ContentValues;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import java.io.IOException;
import java.util.ArrayList;

import static com.example.myapplication.R.id;
import static com.example.myapplication.R.layout;


public class MainActivity extends AppCompatActivity {

    //current frame
    static int count=0;
    ArrayList<Integer> amount= new ArrayList<>();
    LinearLayout main;

    //database
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    static boolean flag=false;
    private static final String TAG = "database";

    Dialog_fragment dialog_fragment;
    Person_fragment person_fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ImageButton button_back, button_forward,button_settings;

    ArrayList<Integer> backgrounds_array = new ArrayList<>();
    ArrayList<Integer>  persons_array = new ArrayList<>();
    ArrayList<String> name_array = new ArrayList<>();
    ArrayList<String> texts_array = new ArrayList<>();

    //music
    SoundPool soundPool;
    int fontSound;

    LinearLayout person_layout ;
    static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        //initialization
        main = findViewById(id.main_layout);
        dialog_fragment  = new Dialog_fragment();
        person_fragment = new Person_fragment();

        fragmentManager = getSupportFragmentManager();
        initPersonFragment();
        initDialogFragment();
        button_back = findViewById(id.image_back);
        button_forward = findViewById(id.image_forward);
        button_settings = findViewById(id.settings);
        mainActivity = this;
        //music
        playMusic();

        //database
        mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        //Clickers
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == false){
                switch (count){
                    case 1:
                        flag=true;
                        toNextSlide();
                        case1();
                        break;
                    case 11:
                        flag=true;
                        if(amount.size()<3){
                        case11();
                        } else{
                            count=27;
                            flag=false;
                        }
                        toNextSlide();
                        break;
                    case 15: case15(); break;
                    case 17: count=11; break;
                    case 18:
                        flag=true;
                        toNextSlide();
                        case18();
                        break;
                    case 19:
                        flag=true;
                        toNextSlide();
                        case19();
                        break;
                    case 23: count=11; break;
                    case 25:
                        flag=true;
                        toNextSlide();
                        case25();
                        break;
                    case 27: count=11; break;
                    case 32: finish(); break;
                    default:
                        toNextSlide();
                }

                }
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count!=0 && count!=1 && count!=7){
                    count=count-2;
                    toNextSlide();
                    count++;
                }
            }
        });

        button_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                intent.putExtra("count", count);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        //toNextSlide();
//        if(count == 0){
//            main.setBackgroundResource(0);
//            person_layout.setBackgroundResource(0);
//        }
        if(count==16) {
            changeDialogText();
            main.setBackground(getResources().getDrawable(R.drawable.bathroom));
            LinearLayout person_layout = findViewById(R.id.person_fragment);
            person_layout.setBackgroundResource(0);
            count++;
        }


    }
    @Override
    protected void onResume() {
        super.onResume();
        setDataToArrays();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBHelper.close();
    }


    public void case1(){
        person_layout = findViewById(id.person_fragment);
        final Button btn = new Button(this);
        btn.setText("Отключить будильник");
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(imageViewLayoutParams);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                toNextSlide();
                count++;
                person_layout.removeView(btn);
            }
        });
        person_layout.addView(btn,0);
    }

    public void case11(){
        person_layout = findViewById(id.person_fragment);
        final Button btn = new Button(this);
        final Button btn1 = new Button(this);
        final Button btn2 = new Button(this);

        btn.setText("Душ");
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(imageViewLayoutParams);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.add(0);
                count=12;
                person_layout.removeView(btn);
                person_layout.removeView(btn1);
                person_layout.removeView(btn2);
                flag=false;
                toNextSlide();
            }
        });



        btn1.setText("Кухня");
        btn1.setLayoutParams(imageViewLayoutParams);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.add(1);
                count=17;
                person_layout.removeView(btn);
                person_layout.removeView(btn1);
                person_layout.removeView(btn2);
                flag=false;
                toNextSlide();
            }
        });


        btn2.setText("Проверить отчет");
        btn2.setLayoutParams(imageViewLayoutParams);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.add(2);
                count=23;
                person_layout.removeView(btn);
                person_layout.removeView(btn1);
                person_layout.removeView(btn2);
                flag=false;
                toNextSlide();
            }
        });
        if(amount.isEmpty()){
            person_layout.addView(btn,0);
            person_layout.addView(btn1,0);
            person_layout.addView(btn2,0);
        } else {
            if(!amount.contains(0)){  person_layout.addView(btn,0);}
            if(!amount.contains(1)){  person_layout.addView(btn1,0);}
            if(!amount.contains(2)){  person_layout.addView(btn2,0);}
        }

    }

    public void case15(){
        count++;
        Intent intent = new Intent(getApplicationContext(), MirrorChooseApperance.class);
        startActivity(intent);
    }
    public  void case18(){
        person_layout = findViewById(id.person_fragment);
        final Button btn = new Button(this);
        final Button btn1 = new Button(this);

        btn.setText("Чай");
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(imageViewLayoutParams);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                toNextSlide();
                count++;
                person_layout.removeView(btn);
                person_layout.removeView(btn1);
                case19();
            }
        });


        btn1.setText("Кофе");
        btn1.setLayoutParams(imageViewLayoutParams);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                toNextSlide();
                count++;
                person_layout.removeView(btn);
                person_layout.removeView(btn1);
                case19();
            }
        });
        person_layout.addView(btn,0);
        person_layout.addView(btn1,0);

    }

    public void case19(){
        person_layout = findViewById(id.person_fragment);
        final Button btn = new Button(this);
        btn.setText("Налить кипяток");
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(imageViewLayoutParams);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                toNextSlide();
                count++;
                person_layout.removeView(btn);
            }
        });
        person_layout.addView(btn,0);
    }

    public void case25(){
        person_layout = findViewById(id.person_fragment);
        final Button btn = new Button(this);
        btn.setText("Проверка");
        LinearLayout.LayoutParams imageViewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(imageViewLayoutParams);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                toNextSlide();
                count++;
                person_layout.removeView(btn);
            }
        });
        person_layout.addView(btn,0);
    }
    public void toNextSlide(){
        if(count == 0){
            //soundPool.play(fontSound,1,1,0,-1,1);
        }
        changeBackground();
        changePersonBackground();
        changeDialogText();
        count++;
    }

    public void setDataToArrays(){
        Cursor cursor1 = mDb.rawQuery("select * from dialogs ",null);
        cursor1.moveToFirst();
        Cursor cursor2 = mDb.rawQuery("select * from backgrounds ",null);
        cursor2.moveToFirst();
        Cursor cursor3 = mDb.rawQuery("select * from persons ",null);
        cursor3.moveToFirst();
        while (!cursor1.isAfterLast()) {
            name_array.add(cursor1.getString(1));
            texts_array.add(cursor1.getString(2));
            cursor1.moveToNext();
        }
        cursor1.close();
        while (!cursor2.isAfterLast()) {
            backgrounds_array.add(Integer.valueOf(cursor2.getString(1)));
            cursor2.moveToNext();
        }
        cursor2.close();
        while (!cursor3.isAfterLast()) {
            persons_array.add(Integer.valueOf(cursor3.getString(1)));
            cursor3.moveToNext();
        }
        cursor3.close();
    }

    public void changeBackground(){
        final TypedArray background_images = getResources().obtainTypedArray(R.array.bakgrounds);
        main.setBackground(background_images.getDrawable(backgrounds_array.get(count)));
    }
    public void changePersonBackground(){
        LinearLayout person_layout = findViewById(R.id.person_fragment);
        final TypedArray person_images = getResources().obtainTypedArray(R.array.persons);
        if(persons_array.get(count) == -1){
        person_layout.setBackgroundResource(0);
        } else {
        person_layout.setBackground(person_images.getDrawable(persons_array.get(count)));
        }
    }
    public void changeDialogText(){
        TextView name1 = findViewById(id.text_name);
        name1.setText(name_array.get(count));
        TextView textView = findViewById(R.id.dialog_text);
        textView.setText(texts_array.get(count));

    }

    public static MainActivity getInstance(){
        return  mainActivity;
    }

    public void initPersonFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id.person, new Person_fragment());
        fragmentTransaction.commit();
    }
    public void initDialogFragment(){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id.dialog, new Dialog_fragment());
        fragmentTransaction.commit();
    }
    public void playMusic(){//music
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else{
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }

        fontSound = soundPool.load(this, R.raw.font,1);
    }
}