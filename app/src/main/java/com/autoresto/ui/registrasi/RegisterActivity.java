package com.autoresto.ui.registrasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.autoresto.R;
import com.autoresto.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText eName, eUsername, eEmail, ePhone, eGender, eBirthday, ePassword, eConfPass;
    private Button btnRegister;
    private ProgressDialog loading;

    private Context mContext;
  //  ApiInterface mApiService;

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
        //mApiService = ApiUtils.getAPIService();

        initComponents();

    }

    private void initComponents() {
        eName = (EditText) findViewById(R.id.txt_name);
        eUsername = (EditText) findViewById(R.id.txt_username);
        eEmail = (EditText) findViewById(R.id.txt_email);
        ePhone = (EditText) findViewById(R.id.txt_phone);
        eGender = (EditText) findViewById(R.id.txt_gender);
        eBirthday = (EditText) findViewById(R.id.txt_birthdate);
        ePassword = (EditText) findViewById(R.id.txt_pass);
        eConfPass = (EditText) findViewById(R.id.txt_confPass);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eName.length() == 0 ||
                        eUsername.length() == 0 ||
                        eEmail.length() == 0 ||
                        ePhone.length() == 0 ||
                        eGender.length() == 0 ||
                        eBirthday.length() == 0 ||
                        ePassword.length() == 0 ||
                        eConfPass.length() == 0 )  {
                    Toast.makeText(mContext, "Field tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
                } else if (ePassword.getText().toString().equals(eConfPass.getText().toString())) {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    startActivity(new Intent(mContext, LoginActivity.class));
                    // requestRegister();
                } else{
                    Toast.makeText(mContext, "Password tidak cocok!" +ePassword.getText().toString() +" " +eConfPass.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


//    private void requestRegister(){
//        mApiService.registerRequest(eName.getText().toString(),
//                eUsername.getText().toString(),
//                eEmail.getText().toString(),
//                eTelp.getText().toString(),
//                eGender.getText().toString(),
//                eBirthday.getText().toString(),
//                ePassword.getText().toString(),
//                3)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.isSuccessful()){
//                            Log.i("debug", "onResponse: Berhasil");
//                            loading.dismiss();
//                            try {
//                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                                if (response.code() == 201){
//                                    Toast.makeText(mContext, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(mContext, LoginActivity.class));
//                                } else {
//                                    String error_message = jsonRESULTS.getString("message");
//                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            Log.i("debug", "onResponse: Gagal");
//                            Toast.makeText(mContext, "Register Gagal! Ulangi lagi", Toast.LENGTH_SHORT).show();
//                            loading.dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
//                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
//                        loading.dismiss();
//                    }
//                });
//    }
}
