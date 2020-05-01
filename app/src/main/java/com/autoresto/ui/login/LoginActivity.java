package com.autoresto.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.autoresto.MainActivity;
import com.autoresto.R;
import com.autoresto.ui.registrasi.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private EditText eUsername, ePassword, eRoleId;
    private String sUsername, sPassword;
    private Button btnLogin, btnRegister;
    private ProgressDialog loading;

    private Context mContext;
    //ApiInterface mApiService;
    private TextView tvRegister;

    private Boolean session = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
//        session = sharedPreferences.getBoolean(Constans.SESSION, false);
//        if(session) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }

        mContext = this;
//        mApiService = ApiUtils.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();

    }

    private void initComponents() {
        eUsername = (EditText) findViewById(R.id.txt_username);
        ePassword = (EditText) findViewById(R.id.txt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvRegister = (TextView) findViewById(R.id.tv_daftar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eUsername.length() == 0) {
                    Toast.makeText(mContext, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if(ePassword.length() == 0) {
                    Toast.makeText(mContext, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    // requestLogin();
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

//    private void requestLogin(){
//        mApiService.loginRequest(eUsername.getText().toString(), ePassword.getText().toString(), 3)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                        if (response.isSuccessful()){
//                            loading.dismiss();
//                            try {
//                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                                if (response.code() == 200){
//                                    // Jika login berhasil maka data nama yang ada di response API
//                                    String id = jsonRESULTS.getString("id");
//                                    String token = jsonRESULTS.getString("access_token");
//                                    String name = jsonRESULTS.getString("name");
//                                    Toast.makeText(mContext, "Selamat Datang Sobat Parkir " + name, Toast.LENGTH_SHORT).show();
//                                    sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putString(Constans.TAG_ID_USER, id);
//                                    editor.putString(Constans.TAG_TOKEN, token);
//                                    editor.putBoolean(Constans.SESSION, true);
//                                    editor.apply();
//
//                                    Intent intent = new Intent(mContext, MainActivity.class);
//                                    startActivity(intent);
//                                } else if (response.code() == 400) {
//                                    // Jika login gagal
//                                    String error_message = jsonRESULTS.getString("message");
//                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            Toast.makeText(mContext, "Login Gagal! Coba lagi", Toast.LENGTH_SHORT).show();
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
//
//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this).setTitle("Keluar")
//                .setMessage("Keluar aplikasi ?")
//                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        moveTaskToBack(true);
//                    }
//                }).setNegativeButton("Tidak", null).show();
//    }
}
