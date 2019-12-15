package com.example.readbook.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.readbook.Model.Book;
import com.example.readbook.R;
import com.example.readbook.ReadBookActivity;
import com.example.readbook.adapter.BookListAdapter;
import com.example.readbook.ui.favorite.FavoriteViewModel;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements OnLoadCompleteListener{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    public static ListView lvBookList;
    FloatingActionButton fab;

    BookListAdapter bookAdapter;
    static ArrayList<Book> bookList;

    private static final int PERMISSION_CODE = 42042;
    private static final int REQUEST_CODE = 42;
    File file = null;
    PDFView pdfView;
    Uri uri;

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
        final View root = inflater.inflate(R.layout.fragment_recently, container, false);
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
        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdfView = root.findViewById(R.id.pdfView);
                pickFile();
            }
        });

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


    void pickFile() {
        int permissionCheck = ContextCompat.checkSelfPermission(this.getActivity(),
                READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this.getActivity(),
                    new String[]{READ_EXTERNAL_STORAGE},
                    PERMISSION_CODE
            );

            return;
        }

        launchPicker();
    }

    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchPicker();
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            Intent intent = new Intent(this.getActivity(), ReadBookActivity.class);
            intent.putExtra("uri", uri.toString());
            startActivity(intent);
        }
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());


    }

}