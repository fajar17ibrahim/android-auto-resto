package com.autoresto.ui.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.autoresto.R;
import com.autoresto.ui.menu.drink.DrinkFragment;
import com.autoresto.ui.menu.drink.ListDrinkAdapter;
import com.autoresto.ui.menu.food.FoodFragment;
import com.google.android.material.tabs.TabLayout;

public class MenuFragment extends Fragment {

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

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

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return root;
    }

    private void setupViewPager(ViewPager mViewPager) {
        mSectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        mSectionsPageAdapter.addFragment(new FoodFragment(), "Makanan");
        mSectionsPageAdapter.addFragment(new DrinkFragment(), "Minuman");
        mViewPager.setAdapter(mSectionsPageAdapter);

    }

}