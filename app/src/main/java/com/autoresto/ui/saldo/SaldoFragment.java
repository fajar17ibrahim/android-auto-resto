package com.autoresto.ui.saldo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.autoresto.R;
import com.autoresto.model.User;
import com.autoresto.ui.account.AccountContract;
import com.autoresto.ui.account.AccountPresenter;
import com.autoresto.utils.Constans;

public class SaldoFragment extends Fragment implements AccountContract.View {

    private SharedPreferences sharedPreferences;

    private String token;

    private AccountPresenter accountPresenter;

    private ProgressBar progressBar;

    private TextView tvBalance;
    private TextView tvVirtualAccount;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_saldo, container, false);

        sharedPreferences = getActivity().getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

        progressBar = (ProgressBar) root.findViewById(R.id.pb_loading);

        tvBalance = (TextView) root.findViewById(R.id.tv_balance);
        tvVirtualAccount = (TextView) root.findViewById(R.id.tv_virtual_account);

        accountPresenter = new AccountPresenter(this);
        accountPresenter.requestDataFromServer(token);

        return root;
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
        tvBalance.setText("Rp " + user.getBalance());
        tvVirtualAccount.setText(user.getVirtual_account());
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("Error Response ", throwable.getMessage());
        Toast.makeText(getActivity(), "Data gagal dimuat.", Toast.LENGTH_LONG).show();
    }
}