package com.sobatparkir.mainactivity.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sobatparkir.mainactivity.R;
import com.sobatparkir.mainactivity.network.ApiInterface;
import com.sobatparkir.mainactivity.ui.login.LoginActivity;
import com.sobatparkir.mainactivity.utils.ApiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText eName, eUsername, eEmail, eTelp, eGender, eBirthday, ePassword, eConfPass;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    ApiInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        mApiService = ApiUtils.getAPIService();

        initComponents();

    }

    private void initComponents() {
        eName = (EditText) findViewById(R.id.txt_name);
        eUsername = (EditText) findViewById(R.id.txt_username);
        eEmail = (EditText) findViewById(R.id.txt_email);
        eTelp = (EditText) findViewById(R.id.txt_telp);
        eGender = (EditText) findViewById(R.id.txt_jk);
        eBirthday = (EditText) findViewById(R.id.txt_birthdate);
        ePassword = (EditText) findViewById(R.id.txt_pass);
        eConfPass = (EditText) findViewById(R.id.txt_confPass);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eName.length() == 0 ||
                        eUsername.length() == 0 ||
                        eUsername.length() == 0 ||
                        eEmail.length() == 0 ||
                        eTelp.length() == 0 ||
                        eGender.length() == 0 ||
                        eBirthday.length() == 0 ||
                        ePassword.length() == 0 ||
                        eConfPass.length() == 0 )  {
                    Toast.makeText(mContext, "Field tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
                } else if (ePassword.getText().toString().equals(eConfPass.getText().toString())) {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    requestRegister();
                } else{
                    Toast.makeText(mContext, "Password tidak cocok!" +ePassword.getText().toString() +" " +eConfPass.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void requestRegister(){
        mApiService.registerRequest(eName.getText().toString(),
                eUsername.getText().toString(),
                eEmail.getText().toString(),
                eTelp.getText().toString(),
                eGender.getText().toString(),
                eBirthday.getText().toString(),
                ePassword.getText().toString(),
                3)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: Berhasil");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (response.code() == 201){
                                    Toast.makeText(mContext, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: Gagal");
                            Toast.makeText(mContext, "Register Gagal! Ulangi lagi", Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }
}
