package com.autoresto.ui.account;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.autoresto.R;
import com.autoresto.model.User;
import com.autoresto.ui.about.AboutActivity;
import com.autoresto.ui.callcenter.CallCenterActivity;
import com.autoresto.ui.editpassword.EditPasswordActivity;
import com.autoresto.ui.editprofile.EditProfileActivity;
import com.autoresto.ui.history.HistoryActivity;
import com.autoresto.ui.login.LoginActivity;
import com.autoresto.ui.service.ServiceActivity;
import com.autoresto.utils.Constans;

public class AccountFragment extends Fragment implements View.OnClickListener, AccountContract.View {

    private SharedPreferences sharedPreferences;

    private String token;

    private AccountPresenter accountPresenter;

    private ProgressBar progressBar;

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;

    private ImageView imgPhoto;

    private LinearLayout llEditProfile;
    private LinearLayout llEditPassword;
    private LinearLayout llService;
    private LinearLayout llCallCenter;
    private LinearLayout llAbout;

    private Button btnLogOut;

    public AccountFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

        progressBar = (ProgressBar) root.findViewById(R.id.pb_loading);

        imgPhoto = (ImageView) root.findViewById(R.id.iv_photo);

        tvName = (TextView) root.findViewById(R.id.tv_name);
        tvPhone = (TextView) root.findViewById(R.id.tv_phone);
        tvEmail = (TextView) root.findViewById(R.id.tv_email);

        LinearLayout llHistory = (LinearLayout) root.findViewById(R.id.ll_history);
        llHistory.setOnClickListener(this);

        llEditProfile = (LinearLayout) root.findViewById(R.id.ll_edit_profile);
        llEditProfile.setOnClickListener(this);

        llEditPassword = (LinearLayout) root.findViewById(R.id.ll_edit_password);
        llEditPassword.setOnClickListener(this);

        llService = (LinearLayout) root.findViewById(R.id.ll_service);
        llService.setOnClickListener(this);

        llCallCenter = (LinearLayout) root.findViewById(R.id.ll_call_center);
        llCallCenter.setOnClickListener(this);

        llAbout = (LinearLayout) root.findViewById(R.id.ll_about);
        llAbout.setOnClickListener(this);

        btnLogOut = (Button) root.findViewById(R.id.btn_log_out);
        btnLogOut.setOnClickListener(this);

        accountPresenter = new AccountPresenter(this);
        accountPresenter.requestDataFromServer(token);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_history:
                Intent iHistory = new Intent(getContext(), HistoryActivity.class);
                startActivity(iHistory);
                break;
            case R.id.ll_edit_profile:
                Intent iEditProfile = new Intent(getContext(), EditProfileActivity.class);
                startActivity(iEditProfile);
                break;
            case R.id.ll_edit_password:
                Intent iEditPassword = new Intent(getContext(), EditPasswordActivity.class);
                startActivity(iEditPassword);
                break;

            case R.id.ll_service:
                Intent iService = new Intent(getContext(), ServiceActivity.class);
                startActivity(iService);
                break;

            case R.id.ll_call_center:
                Intent iCallCenter = new Intent(getContext(), CallCenterActivity.class);
                startActivity(iCallCenter);
                break;

            case R.id.ll_about:
                Intent iAbout = new Intent(getContext(), AboutActivity.class);
                startActivity(iAbout);
                break;

            case R.id.btn_log_out:
                new AlertDialog.Builder(getActivity())
                        .setTitle("Konfirmasi")
                        .setMessage("Anda yakin ingin keluar?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sharedPreferences.edit().remove(Constans.TAG_USER_ID).commit();
                                sharedPreferences.edit().remove(Constans.TAG_TOKEN).commit();
                                sharedPreferences.edit().remove(Constans.SESSION).commit();
                                sharedPreferences.edit().remove(Constans.MY_SHARED_PREFERENCES).commit();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                break;
        }
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
    public void setDataToViews(User user) {
        tvName.setText(user.getName());
        tvPhone.setText(user.getPhone());
        tvEmail.setText(user.getEmail());
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("Error Response ", throwable.getMessage());
        Toast.makeText(getActivity(), throwable.toString(), Toast.LENGTH_LONG).show();
    }
}