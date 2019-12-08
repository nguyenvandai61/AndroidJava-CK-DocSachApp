package com.example.readbook.ui.catalog;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.readbook.Model.Book;
import com.example.readbook.R;
import com.example.readbook.adapter.BookListAdapter;
import com.example.readbook.ui.main.PlaceholderFragment;

import java.util.ArrayList;

public class CatalogFragment extends Fragment {

    private CatalogViewModel catalogViewModel;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        catalogViewModel =
                ViewModelProviders.of(this).get(CatalogViewModel.class);
        View root = inflater.inflate(R.layout.fragment_catalog, container, false);
        final TextView textView = root.findViewById(R.id.text_catalog);
        catalogViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}