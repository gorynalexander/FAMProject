package com.odinn.anotherversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class FullScreenActivity extends AppCompatActivity{


    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppDefault);
        setContentView(R.layout.fullscreen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView iv = (ImageView) findViewById(R.id.imageViewFull);
        //TextView tv = (TextView) findViewById(R.id.textView12);
        //tv.setText("KEK");
        initToolbar();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", R.drawable.fontan);
        iv.setImageResource(id);




    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(R.string.app_name);
    }
}
