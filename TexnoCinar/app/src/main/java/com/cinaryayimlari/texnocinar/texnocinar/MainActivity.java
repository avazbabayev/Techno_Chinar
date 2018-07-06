package com.cinaryayimlari.texnocinar.texnocinar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Book> lstBook ;
    private Toolbar mtoolbar;
    private int DEFAULT_SPAN_COUNT =2;
    private CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
      //  getSupportActionBar().setTitle("Texno Çinar ( Çinar Yayımları )");



        lstBook = new ArrayList<>();

        lstBook.add(new Book("Çinar Sınaq İmtahanı",0,"Description book",R.drawable.azyarpaqtest1));
        lstBook.add(new Book("Çinar Sınaq İmtahanı",2,"Description book",R.drawable.sinaq_imtahan));
        lstBook.add(new Book("Azərbaycan dili Yarpaq Test",0,"Description book",R.drawable.azyarpaqtest1));
        lstBook.add(new Book("Ana dili Yarpaq Test 1",1,"Description book",R.drawable.azyarpaqtest1));
        lstBook.add(new Book("Ana dili Yarpaq Test 2",1,"Description book",R.drawable.azyarpaqtest2));
        lstBook.add(new Book("Ana dili Yarpaq Test 3",1,"Description book",R.drawable.azarpaqtest3));
        lstBook.add(new Book("Ana dili Yarpaq Test 4",1,"Description book",R.drawable.azyarpaqtest4));
        lstBook.add(new Book("Riyaziyyat Yarpaq Test",0,"Description book",R.drawable.riyaziyyatyarpaqtest1));
        lstBook.add(new Book("Riyaziyyat Yarpaq Test 1",1,"Description book",R.drawable.riyaziyyatyarpaqtest1));
        lstBook.add(new Book("Riyaziyyat Yarpaq Test 2",1,"Description book",R.drawable.riyaziyyatyarpaqtest2));
        lstBook.add(new Book("Riyaziyyat Yarpaq Test 3",1,"Description book",R.drawable.riyaziyyatyarpaqtest3));
        lstBook.add(new Book("Riyaziyyat Yarpaq Test 4",1,"Description book",R.drawable.riyaziyyatyarpaqtest4));
        lstBook.add(new Book("Beyin Gimnastikası Rəqəmli Məntiq",0,"Description book",R.drawable.mentiqreqemli1));
        lstBook.add(new Book("Rəqəmli Məntiq 1",1,"Description book",R.drawable.mentiqreqemli1));
        lstBook.add(new Book("Rəqəmli Məntiq 2",1,"Description book",R.drawable.mentiqreqemli2));
        lstBook.add(new Book("Rəqəmli Məntiq 3",1,"Description book",R.drawable.mentiqreqemli3));
        lstBook.add(new Book("Rəqəmli Məntiq 4",1,"Description book",R.drawable.mentiqreqemli4));
        lstBook.add(new Book("Beyin Gimnastikası Şəkilli Məntiq",0,"Description book",R.drawable.mentiqsekilli1));
        lstBook.add(new Book("Şəkilli Məntiq 1",1,"Description book",R.drawable.mentiqsekilli1));
        lstBook.add(new Book("Şəkilli Məntiq 2",1,"Description book",R.drawable.mentiqsekilli2));
        lstBook.add(new Book("Şəkilli Məntiq 3",1,"Description book",R.drawable.mentiqsekilli3));
        lstBook.add(new Book("Şəkilli Məntiq 4",1,"Description book",R.drawable.mentiqsekilli4));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);


        myrv.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        myrv.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, DEFAULT_SPAN_COUNT);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook, gridLayoutManager, DEFAULT_SPAN_COUNT);
        myrv.setLayoutManager(gridLayoutManager);
        myrv.setAdapter(myAdapter);
        if(!isNetworkAvailable()){
            dialogshow();
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void dialogshow(){
        AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Internet xətası");
        builder  .setMessage("Programı istifadə etmək üçün internet xəttini açın sonra OK düyməsinə klikləyin");
        builder .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Çıxış", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });
        builder .setIcon(android.R.drawable.ic_dialog_alert);
        builder   .setCancelable(false);

        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }
}
