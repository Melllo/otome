package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dialog_fragment extends Fragment {


    LinearLayout linearLayout;
    TextView textView;
    String text_array[] = {"One","Two","Three","Four","Five","Six"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        textView = view.findViewById(R.id.dialog_text);
        //textView.setText("Hello");
        return view;
    }

    public void changeDialogText(String count){
        TextView textView = getView().findViewById(R.id.dialog_text);
        textView.setText(count);
    }

}

