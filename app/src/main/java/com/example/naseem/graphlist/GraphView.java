package com.example.naseem.graphlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class GraphView extends AppCompatActivity {
    BarChart barChart;
    BarData barData;
    SPData spdata;
    ArrayList<String> sub_names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view);

        spdata = new SPData(getApplicationContext());
        barChart = (BarChart) findViewById(R.id.chart);
        ArrayList<String> examname = getJsonData(spdata.getData(SPData.MAK));
        sub_names = getJsonSub(spdata.getData(SPData.SUB));
        barData = new BarData(examname,getJsonMarks(spdata.getData(SPData.MAK),examname));
        getJsonData(spdata.getData(SPData.MAK));
        getJsonSub(spdata.getData(SPData.SUB));
        barChart.setData(barData);
    }

    private ArrayList<String> getJsonData(String data) {
        ArrayList<String> allExamNames = new ArrayList<>();
        HashSet<String> unique= new HashSet<String>() ;
        ArrayList<String> uniqueExamNames = new ArrayList<String>();
        try {
            JSONArray json = new JSONArray(data);
            for(int i = 0 ; i< json.length();i++) {
                JSONObject job = json.getJSONObject(i);
                allExamNames.add(job.getString("exam_name"));

            }
            //Log.e("all exam names",allExamNames.toString());
            unique = new HashSet<String>(allExamNames);

            uniqueExamNames = new ArrayList<String>(unique);
            Collections.sort(uniqueExamNames);
            //Log.e("UNIQUE",uniqueExamNames.toString());
            return uniqueExamNames;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private ArrayList<String> getJsonSub(String data) {
        ArrayList<String> subnames = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(data);
            for (int i = 0; i < json.length(); i++) {
                JSONObject job = json.getJSONObject(i);

                subnames.add(job.getString("sub_name"));
            }

            return subnames;

        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }


    }






    private List<BarDataSet> getJsonMarks(String data, ArrayList<String> examNames) {
        List<BarDataSet> dataset = new ArrayList<BarDataSet>();
        List<BarEntry> entry = new ArrayList<>();
        List<List<BarEntry>> examData = new ArrayList<>();

        try {
            BarDataSet bardata;
            BarEntry barEntry;
            JSONArray json = new JSONArray(data);

            for(int j = 0 ; j<sub_names.size()-1;j++){

                for(int i = 0 ; i < examNames.size();i++){
                    JSONObject job = json.getJSONObject(i);
                    //Log.e("json",job.getString("marks"));
                    barEntry = new BarEntry(Integer.parseInt(job.getString("marks")),i);
                    entry.add(barEntry);

                }

                bardata  = new BarDataSet(entry,sub_names.get(j));
                bardata.setColor(Color.rgb(0,j,j));
                dataset.add(bardata);
            }
            // Log.e("datasetlength",dataset.size()+"")  ;


            return dataset;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
