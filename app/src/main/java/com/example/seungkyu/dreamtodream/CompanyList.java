package com.example.seungkyu.dreamtodream;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CompanyList extends AppCompatActivity {
    private String TAG = CompanyList.class.getSimpleName();
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    ListViewAdapter adapter;
    ListView listview;
    JSONArray jsonArray;
    ImageView imageView;

    ArrayList<CompanyVO> companyVOs = new ArrayList<CompanyVO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);

        new CreateCompanyList().execute();
        imageView = (ImageView)findViewById(R.id.image);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Adapter 생성
        adapter = new ListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        /*for(int i=0; i<jsonArray.length(); i++){
            adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_oldman),jsonArray.get(i));
        }*/
        // 첫 번째 아이템 추가.
        /*adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_oldman), "Box","Account Box Black 36dp");
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_man), "Circle","Account Circle Black 36dp");
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        // ...
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드
        adapter.addItem(ContextCompat.getDrawable(this,R.drawable.ic_boy), "Ind","Assignment Ind Black 36dp");
        // 기본 생성 코드 및 ListView와 Adapter 생성 코드*/


        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                CompanyVO item = (CompanyVO)parent.getItemAtPosition(position);

                String titleStr = item.getC_name();
                String descStr = item.getC_giveTo();
                String imgPath = item.getC_imagePath();

                Intent intent = new Intent(getApplicationContext(), CompanyViewPage.class);
                startActivity(intent);

                String toastMessage = item.getC_name()+" is selected.";
                Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDrawerItems(){
        String[] osArray = {"Android","ios","Windows","Board"};
        mAdapter = new ArrayAdapter<String>(CompanyList.this, android.R.layout.simple_list_item_1,osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Board.class);
                startActivity(intent);
                Toast.makeText(CompanyList.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

    private class CreateCompanyList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient client = new DefaultHttpClient();
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 5000);
            HttpConnectionParams.setSoTimeout(params, 5000);
            HttpGet httpGet = new HttpGet("http://52.78.129.84:3000/getCompanyList");
            HttpResponse response = null;
            try {
                response = client.execute(httpGet);

                HttpHandler handler = new HttpHandler();
                InputStream in = new BufferedInputStream(response.getEntity().getContent());
                String result = handler.convertStreamToString(in);
                Log.e(TAG, "Response : "+result);

                jsonArray = new JSONArray(result);

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject jobj = jsonArray.getJSONObject(i);
                    companyVOs.add(new CompanyVO(jobj.getString("c_name"),
                            jobj.getString("c_giveTo"), jobj.getString("c_imagePath")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for(int i=0; i<companyVOs.size(); i++){
                adapter.addItem(companyVOs.get(i).getC_imagePath(),
                        companyVOs.get(i).getC_name(), companyVOs.get(i).getC_giveTo());
            }
        }
    } // CreateCompanyList Class

}