package com.autoresto.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.autoresto.R;
import com.autoresto.model.User;
import com.autoresto.session.TroliSession;
import com.autoresto.ui.account.AccountContract;
import com.autoresto.ui.account.AccountPresenter;
import com.autoresto.ui.menu.drink.DrinkFragment;
import com.autoresto.ui.menu.drink.ListDrinkAdapter;
import com.autoresto.ui.menu.food.FoodFragment;
import com.autoresto.ui.menu.food.ListFoodAdapter;
import com.autoresto.ui.trolley.TrolleyActivity;
import com.autoresto.utils.Constans;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MenuFragment extends Fragment implements View.OnClickListener, AccountContract.View {
    private TroliSession dataTroliSession;

    private SharedPreferences sharedPreferences;

    private String token;

    private AccountPresenter accountPresenter;

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private FloatingActionButton btnAddTrolley;

    private AppBarLayout appBarLayout;

    private CollapsingToolbarLayout collapsingToolbar;

    private Toolbar toolbar;

    private TextView tvSaldo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) root.findViewById(R.id.container);
        setupViewPager(mViewPager);

        sharedPreferences = getActivity().getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");

        NestedScrollView nestedScrollView = (NestedScrollView) root.findViewById(R.id.nsv_menu);
        nestedScrollView.setFillViewport(true);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        btnAddTrolley = (FloatingActionButton) root.findViewById(R.id.btn_add_trolley);
        btnAddTrolley.setOnClickListener(this);

        tvSaldo = (TextView) root.findViewById(R.id.tv_balance);

        collapsingToolbar = root.findViewById(R.id.collapsing_toolbar);
        toolbar = root.findViewById(R.id.toolbar);
        collapsingToolbar.setTitle("Auto Resto (1)");

        appBarLayout = root.findViewById(R.id.appbar1);
        appBarLayout.setExpanded(true);

        accountPresenter = new AccountPresenter(this);
        accountPresenter.requestDataFromServer(token);

        initCollapsingToolbar();

        return root;
    }

    private void setupViewPager(ViewPager mViewPager) {
        mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mSectionsPageAdapter.addFragment(new FoodFragment(), "Makanan");
        mSectionsPageAdapter.addFragment(new DrinkFragment(), "Minuman");
        mViewPager.setAdapter(mSectionsPageAdapter);

    }

    private void initCollapsingToolbar() {

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Menu (1)");
                    toolbar.setTitle("Menu (1)");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("Auto Resto (1)");
                    toolbar.setTitle("Auto Resto (1)");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_trolley:
                TroliSession troliSession = TroliSession.getInstance();
                if (troliSession.getTroliDataList().size() != 0) {
                    Intent iTrolley = new Intent(getContext(), TrolleyActivity.class);
                    startActivity(iTrolley);
                } else {
                    Toast.makeText(getActivity(), "Pilih menu yang ingin anda pesan!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToViews(User user) {
        tvSaldo.setText("Rp. " + user.getBalance() + ",-");
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}