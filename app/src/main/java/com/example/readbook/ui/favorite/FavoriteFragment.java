package com.example.readbook.ui.favorite;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.readbook.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;

public class FavoriteFragment extends Fragment {

    private static final int PERMISSION_CODE = 42042;
    private static final int REQUEST_CODE = 42;
    private FavoriteViewModel favoriteViewModel;
    File file = null;
    PDFView pdfView;
    Uri uri;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        pdfView = root.findViewById(R.id.pdfView);
        pickFile();

        return root;
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
            displayFromUri(uri);
        }
    }

    private void displayFromUri(Uri uri) {


        pdfView.fromUri(uri)
                //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .password(null)
                .scrollHandle(null)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        pdfView.setMinZoom(1f);
                        pdfView.setMidZoom(5f);
                        pdfView.setMaxZoom(10f);
                        pdfView.zoomTo(2f);
                        pdfView.scrollTo(100,0);
                        pdfView.moveTo(0f,0f);
                    }
                })
                .load();

    }

}
