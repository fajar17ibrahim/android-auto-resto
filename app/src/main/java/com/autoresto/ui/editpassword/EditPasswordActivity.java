package com.autoresto.ui.editpassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.autoresto.R;
import com.autoresto.model.ChangePassword;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.utils.ApiUtils;
import com.autoresto.utils.Constans;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasswordActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private String token;

    private EditText ePasswordOld, ePassword, eConfPass;

    private Button btnSave;

    private ProgressDialog loading;

    private Context mContext;
    //  ApiInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

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

        mContext = this;
        //mApiService = ApiUtils.getAPIService();

        initComponents();
    }

    private void initComponents() {
        ePasswordOld = (EditText) findViewById(R.id.txt_pass_old);
        ePassword = (EditText) findViewById(R.id.txt_pass);
        eConfPass = (EditText) findViewById(R.id.txt_confPass);
        btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ePasswordOld.length() == 0 ||
                        ePassword.length() == 0 ||
                        eConfPass.length() == 0 )  {
                    Toast.makeText(mContext, "Field tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
                } else if (ePassword.getText().toString().equals(eConfPass.getText().toString())) {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    ChangePassword changePassword = new ChangePassword(ePasswordOld.getText().toString(), ePassword.getText().toString(), eConfPass.getText().toString());
                    sendPassword(token, changePassword);
                } else{
                    Toast.makeText(mContext, "Password tidak cocok!" +ePassword.getText().toString() +" " +eConfPass.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void sendPassword(String token, ChangePassword changePassword) {
        Log.d("Data ", changePassword.toString());
        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<ChangePassword> call = apiService.sendPassword("bearer " + token, changePassword);
        call.enqueue(new Callback<ChangePassword>() {
            @Override
            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {
                loading.dismiss();

                if (response.code() == 201) {
                    try {
                        Toast.makeText(mContext, "Password berhasil diganti.", Toast.LENGTH_LONG).show();
                        ChangePassword changePassword = response.body();
                        Log.d("Response Body ", changePassword.toString());
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                } else if(response.code() == 400 ) {
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().toString());
                        String message = jsonRESULTS.getString("message");
                        Toast.makeText(mContext, message + response.code(), Toast.LENGTH_LONG).show();
                        ChangePassword changePassword = response.body();
                        Log.d("Response Body ", changePassword.toString());
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePassword> call, Throwable t) {
                loading.dismiss();
                Log.d("Error Response ", t.toString());
            }
        });
    }
}
