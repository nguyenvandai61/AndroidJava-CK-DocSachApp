package com.example.readbook;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

public class ReadBookActivity extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.pdf_viewer);

        Uri uri = Uri.parse(getIntent().getStringExtra("uri"));
        pdfView = findViewById(R.id.pdfView);
        if (uri != null)
        displayFromUri(uri);


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
