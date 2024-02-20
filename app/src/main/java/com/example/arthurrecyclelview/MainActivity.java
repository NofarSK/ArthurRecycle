package com.example.arthurrecyclelview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private  CustomerAdapter adapter;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSet = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        search = findViewById(R.id.editTextText);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0 ; i < MyData.nameArray.length ; i++ ){
            dataSet.add((new DataModel(
                    MyData.nameArray[i],
                    MyData.description[i],
                    MyData.drawableArray[i]
            )));
        }

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        adapter = new CustomerAdapter(dataSet);
        recyclerView.setAdapter(adapter);
    }

    private void filter(String text)
    {
        ArrayList<DataModel> filteredList = new ArrayList<>();

        for (DataModel data : dataSet){
            if (data.getName().toLowerCase().contains(text.toLowerCase()) ||
                    data.getDescription().toLowerCase().contains(text.toLowerCase())){

                filteredList.add(data);
            }
        }

        adapter.filterList(filteredList);
    }
}