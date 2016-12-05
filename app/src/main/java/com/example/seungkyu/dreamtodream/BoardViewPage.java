package com.example.seungkyu.dreamtodream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BoardViewPage extends AppCompatActivity {
    TextView writerTV, dateTV, titleTV, contentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_view_page);

        Intent intent = getIntent();
        writerTV = (TextView)findViewById(R.id.writerTextView);
        dateTV = (TextView)findViewById(R.id.dateTextView);
        titleTV = (TextView)findViewById(R.id.titleTextView);
        contentTV = (TextView)findViewById(R.id.contentTextView);

        writerTV.setText(intent.getExtras().getString("writer"));
        dateTV.setText(intent.getExtras().getString("date"));
        titleTV.setText(intent.getExtras().getString("title"));
        contentTV.setText(intent.getExtras().getString("content"));

    }
}
