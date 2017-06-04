package com.example.vikramkumaresan.delta_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout act;
    TextView red;
    TextView blue;
    TextView green;
    Button redb;
    Button greenb;
    Button blueb;

    ColorDrawable color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("col",Context.MODE_PRIVATE);

        act=(RelativeLayout)findViewById(R.id.activity_main);

        red=(TextView)findViewById(R.id.redtext);
        redb=(Button)findViewById(R.id.redbutton);
        green=(TextView)findViewById(R.id.greentext);
        greenb=(Button)findViewById(R.id.greenbutton);
        blue=(TextView)findViewById(R.id.bluetext);
        blueb=(Button)findViewById(R.id.bluebutton);
        Button reset = (Button)findViewById(R.id.reset);

        String savedcolor=pref.getString("color","#000000");
        act.setBackgroundColor(Color.parseColor(savedcolor));
        textupdater(savedcolor);


        redb.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        color = (ColorDrawable)act.getBackground();
                        String newback=converter(Integer.toHexString(color.getColor()),0);
                        act.setBackgroundColor(Color.parseColor(newback));
                        autosave(newback);
                    }
                }
        );

        blueb.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        color = (ColorDrawable)act.getBackground();
                        String newback=converter(Integer.toHexString(color.getColor()),2);
                        act.setBackgroundColor(Color.parseColor(newback));
                        autosave(newback);
                    }
                }
        );

        greenb.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        color = (ColorDrawable)act.getBackground();
                        String newback=converter(Integer.toHexString(color.getColor()),1);
                        act.setBackgroundColor(Color.parseColor(newback));
                        autosave(newback);
                    }
                }
        );

        reset.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        act.setBackgroundColor(Color.parseColor("#000000"));
                        red.setText("0");
                        green.setText("0");
                        blue.setText("0");
                        autosave("#000000");
                    }
                }
        );


    }

    String converter(String col,int call){
        String spec1=col.substring(col.length()-6,col.length()).substring(0,2); //Hex Value of Red
        String spec2=col.substring(col.length()-4,col.length()).substring(0,2); //Hex Value of Green
        String spec3=col.substring(col.length()-2,col.length()); //Hex Value of Blue
        int temp;
        String newcol="";
        if(call==0){ //Red Button

            temp=Integer.parseInt(spec1,16)+10; //Hex of red to decimal

            if(temp>255){
                temp=0;
            }
            newcol=Integer.toHexString(temp);

            if(newcol.length()!=2){ //Makes sure you get 2 digits
                newcol="0"+newcol;
            }

            red.setText(""+temp); //Red textbox updated

            return "#"+newcol+spec2+spec3;   //Background Hex Code
        }
        else if(call==1){

            temp=Integer.parseInt(spec2,16)+10;

            if(temp>255){
                temp=0;
            }

            newcol=Integer.toHexString(temp);

            if(newcol.length()!=2){
                newcol="0"+newcol;
            }

            green.setText(""+temp);
            return "#"+spec1+newcol+spec3;
        }
        else{

            temp=Integer.parseInt(spec3,16)+10;

            if(temp>255){
                temp=0;
            }

            newcol=Integer.toHexString(temp);

            if(newcol.length()!=2){
                newcol="0"+newcol;
            }

            blue.setText(""+temp);

            return "#"+spec1+spec2+newcol;
        }
    }

    void autosave(String savecol){
        SharedPreferences pref = getSharedPreferences("col",Context.MODE_PRIVATE);  //For color storage
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("color",savecol);
        edit.apply();
    }

    void textupdater(String col){
        Log.d("TXT",col);
        red.setText(""+Integer.parseInt(col.substring(1,3),16));
        blue.setText(""+Integer.parseInt(col.substring(3,5),16));
        green.setText(""+Integer.parseInt(col.substring(5,7),16));
    }


}
