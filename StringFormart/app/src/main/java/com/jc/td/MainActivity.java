package com.jc.td;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv_introduction = findViewById(R.id.tv_introduction);
        tv_introduction.setText(getResources().getString(R.string.introduction,
                "aohanyao", 26, "aohanyao@gmail.com",123.45));

    }


}
