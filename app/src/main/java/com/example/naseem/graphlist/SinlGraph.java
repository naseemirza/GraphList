package com.example.naseem.graphlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class SinlGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinl_graph);

        LineChart chart=(LineChart)findViewById(R.id.chart);
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0f,0));
        entries.add(new Entry(4f,2));
        entries.add(new Entry(6f,3));
        entries.add(new Entry(7f,4));
        entries.add(new Entry(8f,5));
        entries.add(new Entry(3f,6));
        String[] s = {
                "maths","english","maths","english","maths","english"
        };

        LineDataSet dataset = new LineDataSet(entries, "Hello");
        dataset.disableDashedLine();
        dataset.setColor(Color.BLACK);
        dataset.setDrawFilled(true);
        dataset.setLineWidth(2f);
        dataset.setDrawCubic(true);
        dataset.setFillColor(Color.GREEN);
        dataset.setFillAlpha(65);
        LineData linedata = new LineData(s,dataset);
        chart.setData(linedata);
        chart.animateXY(00,1500);
        chart.invalidate();




    }
}
