package com.autoresto.ui.selecttable;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.autoresto.R;
import com.autoresto.model.Table;
import com.autoresto.ui.login.LoginActivity;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class SelectTableActivity extends AppCompatActivity implements SelectTableContract.View {

    private Context mContex;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private SelectTablePresenter selectTablePresenter;

    private ProgressDialog progressDialog;

    private ProgressBar progressBar;

    private Spinner sListTable;

    private Button btnselect;

    private List<Table> list;

    private ArrayAdapter<Table> adapter;

    private Boolean session_table = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, MODE_PRIVATE);
        session_table = sharedPreferences.getBoolean(Constans.SESSION_TABLE, false);

        if (session_table) {
            Intent iLogin = new Intent(SelectTableActivity.this, LoginActivity.class);
            startActivity(iLogin);
        }

        mContex = this;

        progressBar = (ProgressBar) findViewById(R.id.pb_loading);

        sListTable = (Spinner) findViewById(R.id.s_list_table);

        btnselect = (Button) findViewById(R.id.btn_select);

        list = new ArrayList<>();

        selectTablePresenter = new SelectTablePresenter(this);
        selectTablePresenter.requestDataFromServer();

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(mContex, null, "Loading...", true, false);
                getSelectedTable(v);
            }
        });
    }

    public void getSelectedTable(View v) {
        Table table = (Table) sListTable.getSelectedItem();
        Toast.makeText(mContex, "anda memilih " +table.getName(), Toast.LENGTH_SHORT).show();

        editor = sharedPreferences.edit();
        editor.putString(Constans.TAG_TABLE_ID, String.valueOf(table.getId()));
        editor.putString(Constans.TAG_TABLE_NAME, table.getName());
        editor.putBoolean(Constans.SESSION_TABLE, true);
        editor.apply();
        progressDialog.dismiss();

        Intent iLogin = new Intent(SelectTableActivity.this, LoginActivity.class);
        startActivity(iLogin);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToViews(List<Table> tableList) {
        list.addAll(tableList);

        adapter = new ArrayAdapter<Table>(mContex, R.layout.spinner_table, R.id.tv_spinner_item, list);
        adapter.setNotifyOnChange(true);
        sListTable.setAdapter(adapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(mContex, throwable.toString(), Toast.LENGTH_SHORT).show();
    }
}
