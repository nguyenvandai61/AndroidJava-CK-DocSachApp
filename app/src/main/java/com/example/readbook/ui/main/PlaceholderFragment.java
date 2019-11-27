package com.example.readbook.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.readbook.Model.Book;
import com.example.readbook.R;
import com.example.readbook.adapter.BookListAdapter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    public static ListView lvBookList;

    BookListAdapter bookAdapter;
    static ArrayList<Book> bookList;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recently, container, false);
        Log.v(PlaceholderFragment.class.getName(), "Display");
        SharedPreferences sharedPref = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sharedPref.getBoolean("first_init", true)) {

            bookList = new ArrayList<Book>();
            initDBData();

            bookAdapter = new BookListAdapter(getActivity(), bookList);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("first_init", false);
        }
        lvBookList = root.findViewById(R.id.lv_book_list);

        lvBookList.setAdapter(bookAdapter);

        Log.v(PlaceholderFragment.class.getName(), lvBookList.getItemAtPosition(0).toString());;
        return root;
    }

    private void initDBData() {
        final String path = Environment.getExternalStorageDirectory() + "/Download";

        Book book2 = new Book(10002, "Xử lý tín hiệu số", "Lê A");
        book2.setmPath(path + "/XLTHS.pdf");
        bookList.add(book2);
        Book book3 = new Book(10003, "Lập trình di động", "Lê A");
        book3.setmPath(path + "/XLTHSd.pdf");
        bookList.add(book3);
        Book book = new Book(10001, "Dế mèn Phiêu Lưu Ký", "Lê A");
        book.setmPath(path + "/XLTHS.pdf");
        bookList.add(book);
        Log.v(PlaceholderFragment.class.getName(), "So luong sach "+bookList.size());
    }
}