package com.example.seungkyu.dreamtodream;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class CompanyViewPage extends AppCompatActivity {
    Button startSleep;
    ImageView mImgTrans;
    Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_start_view);






        startSleep = (Button) findViewById(R.id.startSleep);
        mImgTrans = (ImageView) findViewById(R.id.imgTranslate);


        startSleep.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                //Intent intent =  new Intent(getApplicationContext(),StartSleep.class);
                //startActivity(intent);

            }
        });

        new LoadImage().execute("http://52.78.129.84:3000/images/test2.png");
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CompanyViewPage.this);
            pDialog.setMessage("이미지 로딩중입니다...");
            pDialog.show();
        }

        protected Bitmap doInBackground(String... args) {
            try {
                mBitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mBitmap;
        }

        protected void onPostExecute(Bitmap image) {
            if (image != null) {
                mImgTrans.setImageBitmap(image);
                pDialog.dismiss();

            } else {
                pDialog.dismiss();
                Toast.makeText(CompanyViewPage.this, "이미지가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
