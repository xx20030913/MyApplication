package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment;

    private Fragment[] fragments = new Fragment[3];
    private TabLayout tabLayout;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取传递的 Bundle 数据
        bundle = getIntent().getExtras();

        initFragment();

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                replacePage(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        replacePage(0);
    }

    private void initFragment() {
        fragments[0] = new HomeFragment();
        fragments[0].setArguments(bundle); // 将 Bundle 传递给 HomeFragment
        fragments[1] = new MessageFragment();
        fragments[1].setArguments(bundle); // 将 Bundle 传递给 MessageFragment
        fragments[2] = new MineFragment();
        fragments[2].setArguments(bundle); // 将 Bundle 传递给 MineFragment
    }


    private void replacePage(int index) {
        if (index < 0 || index > 2){
            return;
        }
        Fragment fragment = fragments[index];
        if (fragment == null || fragment == currentFragment){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            transaction.add(R.id.ac_navigation_container, fragment);
        }

        Fragment oldFragment = currentFragment;
        if(oldFragment != null){
            transaction.hide(oldFragment).show(fragment).commitNow();
        }else{
            transaction.show(fragment).commitNow();
        }
        currentFragment = fragment;
    }
}