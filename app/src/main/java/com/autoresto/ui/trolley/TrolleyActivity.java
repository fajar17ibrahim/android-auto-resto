package com.autoresto.ui.trolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.autoresto.R;
import com.autoresto.model.Trolley;

import java.util.ArrayList;
import java.util.List;

public class TrolleyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ListTrolleyAdapter listTrolleyAdapter;

    private List<Trolley> trolleyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trolley);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_order);
        showRecyclerList();
    }

    private void showRecyclerList() {
        trolleyList = new ArrayList<>();

        trolleyList.addAll(TrolleyData.getListData());

        listTrolleyAdapter = new ListTrolleyAdapter(this, trolleyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listTrolleyAdapter);


    }
}
