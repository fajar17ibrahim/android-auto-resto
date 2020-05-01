package com.autoresto.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.autoresto.R;
import com.autoresto.ui.editpassword.EditPasswordActivity;
import com.autoresto.ui.editprofile.EditProfileActivity;
import com.autoresto.ui.history.HistoryActivity;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private LinearLayout llHistory, llEditProfile, llEditPassword, llService, llCallCenter, llAbout;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account, container, false);

        llHistory = (LinearLayout) root.findViewById(R.id.ll_history);
        llHistory.setOnClickListener(this);

        llEditProfile = (LinearLayout) root.findViewById(R.id.ll_edit_profile);
        llEditProfile.setOnClickListener(this);

        llEditPassword = (LinearLayout) root.findViewById(R.id.ll_edit_password);
        llEditPassword.setOnClickListener(this);


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

        }
    }
}