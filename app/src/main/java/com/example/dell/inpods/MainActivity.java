package com.example.dell.inpods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.dell.inpods.MainActivity.score;


public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.d("vtp", "onCreate: MainActivity Created");
        setContentView(R.layout.activity_main);
    }
    public void next(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }
}

