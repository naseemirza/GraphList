package com.example.naseem.graphlist;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ListAdapter object;
    ListView list;

    public Button btn1;
    public void init(){
        btn1=(Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,GraphView.class);
                startActivity(it);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        new backg(this).execute();

    }
    class backg extends AsyncTask<Void,Void,String> {

        String json_url;
        Activity activity;

        backg(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {

            json_url = "leadinfosystems.com/school_diary/SchoolDiary/marks.php";

        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL("http://leadinfosystems.com/school_diary/SchoolDiary/marks.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("student_number", "4");

                String abc = builder.build().getQuery();
                bufferedWriter.write(abc);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);

                }
                bufferedReader.close();
                inputStream.close();


                return stringBuilder.toString().trim();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            SPData spData = new SPData(getApplicationContext());
            spData.storeData(result);
            Log.e("result", result);
            String[] res = result.split("@@@");
            try {
                getJsonData(res[0]);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        private void getJsonData(String re) throws JSONException {
            JSONArray json = new JSONArray(re);
            List<String> subjects = new ArrayList<String>();
            for (int i = 0; i <= json.length() - 1; i++) {
                JSONObject jsonobj = json.getJSONObject(i);
                subjects.add(jsonobj.getString("sub_name"));
                //subjects.add(jsonobj.getString("marks"));

            }

            object = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, subjects);
            list = (ListView) findViewById(R.id.list);
            list.setAdapter(object);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent s = new Intent(view.getContext(), Marks.class);
                    startActivity(s);

                }
            });
        }



    }
}
