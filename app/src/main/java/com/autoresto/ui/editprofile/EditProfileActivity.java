package com.autoresto.ui.editprofile;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.autoresto.R;
import com.autoresto.model.ChangeProfile;
import com.autoresto.model.User;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.ui.account.AccountContract;
import com.autoresto.ui.account.AccountPresenter;
import com.autoresto.utils.ApiUtils;
import com.autoresto.utils.Constans;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements AccountContract.View, View.OnClickListener {

    private SharedPreferences sharedPreferences;

    private AccountPresenter accountPresenter;

    private String token;

    private int userId;

    private EditText eName, eUsername, eEmail, ePhone, eGender, eBirthday;

    private int mYear, mMonth, mDay;

    private Button btnSave;

    private ProgressDialog loading;

    private Context mContext;
    //  ApiInterface mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

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

        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        userId = Integer.parseInt(sharedPreferences.getString(Constans.TAG_USER_ID, "user_id"));
        mContext = this;

        accountPresenter = new AccountPresenter(this);
        accountPresenter.requestDataFromServer(token);
        initComponents();

    }

    private void initComponents() {
        eName = (EditText) findViewById(R.id.txt_name);
        eUsername = (EditText) findViewById(R.id.txt_username);
        eEmail = (EditText) findViewById(R.id.txt_email);
        ePhone = (EditText) findViewById(R.id.txt_phone);
        eGender = (EditText) findViewById(R.id.txt_gender);
        eBirthday = (EditText) findViewById(R.id.txt_birthdate);
        btnSave = (Button) findViewById(R.id.btn_save);

        eBirthday.setOnClickListener(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eName.length() == 0 ||
                        eUsername.length() == 0 ||
                        eEmail.length() == 0 ||
                        ePhone.length() == 0 ||
                        eGender.length() == 0 ||
                        eBirthday.length() == 0  )  {
                    Toast.makeText(mContext, "Field tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    ChangeProfile changeProfile = new ChangeProfile(eName.getText().toString(),
                            eUsername.getText().toString(),
                            eEmail.getText().toString(),
                            ePhone.getText().toString(),
                            eGender.getText().toString(),
                            eBirthday.getText().toString());
                    requestRegister(token, changeProfile, userId);
                }

            }
        });
    }


    private void requestRegister(String token, ChangeProfile changeProfile, int userId){
        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<ChangeProfile> call = apiService.updateUser("Bearer " + token, changeProfile, userId);
        call.enqueue(new Callback<ChangeProfile>() {
            @Override
            public void onResponse(Call<ChangeProfile> call, Response<ChangeProfile> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();

                    if ( response.code() ==  200 ) {
                        Toast.makeText(mContext, "Profil berhasil diedit.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().toString());
                            String message = jsonObject.getString("message");
                            Log.d("Message Error ", message);
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangeProfile> call, Throwable t) {
                Log.d("Response Error ", t.toString());
                Toast.makeText(mContext, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToViews(User user) {
        eName.setText(user.getName());
        eUsername.setText(user.getUsername());
        eEmail.setText(user.getEmail());
        ePhone.setText(user.getPhone());
        eBirthday.setText(user.getBirth_day());
        eGender.setText(user.getGender());
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("Error Response ", throwable.toString());
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
