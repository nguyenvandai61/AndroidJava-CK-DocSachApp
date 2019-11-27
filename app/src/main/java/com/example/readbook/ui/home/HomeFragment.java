package com.example.readbook.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.readbook.Model.Book;
import com.example.readbook.R;
import com.example.readbook.adapter.BookListAdapter;
import com.example.readbook.ui.main.PlaceholderFragment;
import com.example.readbook.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentActivity myContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());

        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


//        PlaceholderFragment.lvBookList.setAdapter(bookAdapter);
        return root;
    }


}