package com.autoresto.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.autoresto.R;
import com.autoresto.ui.menu.drink.DrinkFragment;
import com.autoresto.ui.menu.drink.ListDrinkAdapter;
import com.autoresto.ui.menu.food.FoodFragment;
import com.autoresto.ui.trolley.TrolleyActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    private Button btnAddTrolley;

    private AppBarLayout appBarLayout;

    private CollapsingToolbarLayout collapsingToolbar;

    private Toolbar toolbar;


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

        NestedScrollView nestedScrollView = (NestedScrollView) root.findViewById(R.id.nsv_menu);
        nestedScrollView.setFillViewport(true);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        btnAddTrolley = (Button) root.findViewById(R.id.btn_add_trolley);
        btnAddTrolley.setOnClickListener(this);

        collapsingToolbar = root.findViewById(R.id.collapsing_toolbar);
        toolbar = root.findViewById(R.id.toolbar);
        collapsingToolbar.setTitle("Auto Resto");

        appBarLayout = root.findViewById(R.id.appbar1);
        appBarLayout.setExpanded(true);

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
                    collapsingToolbar.setTitle("Menu");
                    toolbar.setTitle("Menu");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("Auto Resto");
                    toolbar.setTitle("Auto Resto");
                    isShow = false;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_trolley:
                Intent iTrolley = new Intent(getContext(), TrolleyActivity.class);
                startActivity(iTrolley);
                break;
        }
    }
}