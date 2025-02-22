package com.autoresto.ui.registrasi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.autoresto.R;
import com.autoresto.model.Register;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.ui.login.LoginActivity;
import com.autoresto.utils.ApiUtils;

import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eName, eUsername, eEmail, ePhone, eBirthday, ePassword, eConfPass;
    private RadioGroup rgGender;
    private RadioButton rbGender;
    private Button btnRegister;
    private ProgressDialog loading;

    private int mYear, mMonth, mDay;

    private Context mContext;

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

        initComponents();

    }

    private void initComponents() {
        eName = (EditText) findViewById(R.id.txt_name);
        eUsername = (EditText) findViewById(R.id.txt_username);
        eEmail = (EditText) findViewById(R.id.txt_email);
        ePhone = (EditText) findViewById(R.id.txt_phone);
        rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        eBirthday = (EditText) findViewById(R.id.txt_birthdate);
        ePassword = (EditText) findViewById(R.id.txt_pass);
        eConfPass = (EditText) findViewById(R.id.txt_confPass);
        btnRegister = (Button) findViewById(R.id.btn_register);

        eBirthday.setOnClickListener(this);

        int idRadioButtonGender = rgGender.getCheckedRadioButtonId();

        rbGender = (RadioButton) findViewById(idRadioButtonGender);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eName.length() == 0 ||
                        eUsername.length() == 0 ||
                        eEmail.length() == 0 ||
                        ePhone.length() == 0 ||
                        eBirthday.length() == 0 ||
                        ePassword.length() == 0 ||
                        eConfPass.length() == 0 )  {
                    Toast.makeText(mContext, "Field tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
                } else if (ePassword.getText().toString().equals(eConfPass.getText().toString())) {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

                    Register register = new Register(eName.getText().toString(),
                            eUsername.getText().toString(),
                            eEmail.getText().toString(),
                            ePassword.getText().toString(),
                            ePhone.getText().toString(),
                            rbGender.getText().toString(),
                            eBirthday.getText().toString());
                    sendRegister(register);
                } else{
                    Toast.makeText(mContext, "Password tidak cocok!" +ePassword.getText().toString() +" " +eConfPass.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void sendRegister(Register register) {
        Log.d("Data ", register.toString());
        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<Register> call = apiService.sendRegister(register);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register > response) {
                loading.dismiss();
                Log.e("Response Code ", String.valueOf(response.code()));

                if (response.code() == 201) {
                    try {
                        Toast.makeText(mContext, "Register berhasil", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(mContext, LoginActivity.class));

                    } catch(Exception e){
                        e.printStackTrace();
                    }
                } else if(response.code() == 401 ) {
                    try {

//                        JSONObject jsonRESULTS = new JSONObject(response.body().toString());
//                        String message = jsonRESULTS.getString("message");
                        Toast.makeText(mContext, "Registrasi gagal. Data username, email, atau nomor telepon yang anda input sudah terdaftar!" + response.code(), Toast.LENGTH_LONG).show();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                loading.dismiss();
                Log.d("Error Response ", t.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if ( v == eBirthday ) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DATE);

            DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eBirthday.setText(year + "-"+ month +"-"+ dayOfMonth);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
