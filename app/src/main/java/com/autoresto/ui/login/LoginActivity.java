package com.autoresto.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.autoresto.MainActivity;
import com.autoresto.R;
import com.autoresto.model.ErrorResponse;
import com.autoresto.network.ApiInterface;
import com.autoresto.ui.registrasi.RegisterActivity;
import com.autoresto.utils.ApiUtils;
import com.autoresto.utils.Constans;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText eUsername, ePassword;
    private Button btnLogin;
    private ProgressDialog loading;

    private Context mContext;
    private ApiInterface mApiService;
    private TextView tvRegister;

    private Boolean session = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(Constans.SESSION, false);
        if(session) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        initComponents();

        setListener();

    }

    private void initComponents() {
        eUsername = (EditText) findViewById(R.id.et_username);
        ePassword = (EditText) findViewById(R.id.et_password);

        btnLogin = (Button) findViewById(R.id.btn_login);

        tvRegister = (TextView) findViewById(R.id.tv_daftar);
    }

    private void setListener () {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eUsername.length() == 0) {
                    Toast.makeText(mContext, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if(ePassword.length() == 0) {
                    Toast.makeText(mContext, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    login();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLogin = new Intent(mContext, RegisterActivity.class);
                startActivity(iLogin);
            }
        });
    }

    private void login(){
        mApiService = ApiUtils.getAPIService();
        mApiService.loginRequest(eUsername.getText().toString(), ePassword.getText().toString(), 2)
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.e("Response Code ", String.valueOf(response.code()));
                    loading.dismiss();

                    if (response.code() == 200){
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            String id = jsonRESULTS.getString("id");
                            String token = jsonRESULTS.getString("access_token");
                            String name = jsonRESULTS.getString("name");
                            Toast.makeText(mContext, "Selamat Datang " + name, Toast.LENGTH_SHORT).show();
                            sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(Constans.TAG_USER_ID, id);
                            editor.putString(Constans.TAG_TOKEN, token);
                            editor.putBoolean(Constans.SESSION, true);
                            editor.apply();

                            Intent intent = new Intent(mContext, MainActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        try {
//                            Log.d("Error response body ", response.errorBody().toString());
//                            JSONObject jsonRESULTS = new JSONObject(response.errorBody().toString());
//                            String message = jsonRESULTS.getString("message");
                            Toast.makeText(mContext, "Login gagal. Username atau Password tidak cocok", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Keluar")
            .setMessage("Keluar aplikasi ?")
            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    moveTaskToBack(true);
                }
            }).setNegativeButton("Tidak", null).show();
    }
}
