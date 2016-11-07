package com.example.naseem.graphlist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Marks extends AppCompatActivity {

    public Button btn;
    public void init(){
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SinlGraph.class));

            }
        });

    }
    List<MarksData> items = new ArrayList<MarksData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);
        init();


        ListView marks =(ListView)findViewById(R.id.marks);
        items.add(new MarksData("10/10/2012",100,40,40));
        items.add(new MarksData("22/10/2014",100,60,60));
        items.add(new MarksData("05/11/2015",100,70,70));
        items.add(new MarksData("10/05/2016",100,40,40));
        items.add(new MarksData("14/08/2016",100,60,60));
        marks.setAdapter(new MyAdaptor());
    }


    class MyAdaptor extends ArrayAdapter<MarksData> {

        public MyAdaptor() {
            super(getApplicationContext(),R.layout.layout, items);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View ItemView = convertView;
            if(ItemView == null){
                ItemView = getLayoutInflater().inflate(R.layout.layout,parent,false);
            }

            MarksData currentItem = items.get(position);
            TextView date = (TextView) ItemView.findViewById(R.id.date);
            TextView total = (TextView) ItemView.findViewById(R.id.total);
            TextView obtained = (TextView) ItemView.findViewById(R.id.obtained);
            TextView percentage = (TextView) ItemView.findViewById(R.id.Percentage);
            date.setText(currentItem.getDate());
            total.setText(currentItem.getTotal()+"");
            obtained.setText(currentItem.getObtained()+"");
            percentage.setText(currentItem.getPercentage()+"");
            return ItemView;
        }
    }
}
