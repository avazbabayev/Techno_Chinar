package com.cinaryayimlari.texnocinar.texnocinar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookItemActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    public  int number2,setquestion =1 ;
    public String youtubelink = "";
    public  YouTubePlayer youTubePlayerl = null;
    public Object syncObject;
    private TextView textView,textView1;
    private ImageView img;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    private List<VideoItem> videoItems = null;
    private List<String> videolinks = null;
    private android.widget.NumberPicker numberPicker1,numberPicker2;
    private boolean setyoutubeplayer = false;
    private FirebaseApp mref;
    private DatabaseReference dbref;
    private RelativeLayout bookrelative;



    public void nextplay(){
        String link = null;
        for(int i = 0;i<videolinks.size()-1;i++)
        {
            if(videolinks.get(i)== youtubelink)
            {
                System.out.println(videolinks.get(i+1));
                youTubePlayerl.loadVideo(videolinks.get(i+1));
                link  = videolinks.get(i+1);
                break;
            }

        }
        if(link!=null){
            youtubelink =link;
        }
    }
    public void previousplay(){
        String link = null;
        for(int i = 1;i<videolinks.size();i++)
        {
            if(videolinks.get(i)== youtubelink)
            {
                youTubePlayerl.loadVideo(videolinks.get(i-1));
                link  = videolinks.get(i-1);
                break;
            }

        }
        if(link!=null){
            youtubelink =link;
        }
    }
    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = Math.round( dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth  = outMetrics.widthPixels / density;



        videoItems = new ArrayList<>();
        videolinks = new ArrayList<>();

        System.out.println("arraylist");

        ImageButton imageButtonplay = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton imageButtonnext = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton imageButtonprev = (ImageButton)findViewById(R.id.imageButton);

        imageButtonplay.getLayoutParams().width = convertDpToPixel(dpWidth/6,this);
        imageButtonplay.getLayoutParams().height=convertDpToPixel(dpHeight/10,this);
        imageButtonnext.getLayoutParams().width = convertDpToPixel(dpWidth/6,this);
        imageButtonnext.getLayoutParams().height=convertDpToPixel(dpHeight/10,this);
        imageButtonprev.getLayoutParams().width = convertDpToPixel(dpWidth/6,this);
        imageButtonprev.getLayoutParams().height=convertDpToPixel(dpHeight/10,this);


        imageButtonprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previousplay();
            }
        });
        imageButtonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextplay();
            }
        });

        imageButtonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(youTubePlayerl.isPlaying()) {
                    youTubePlayerl.pause();
                }
                else {
                    youTubePlayerl.play();
                }
            }
        });


        numberPicker1 = (android.widget.NumberPicker) findViewById(R.id.numberPicker2);
        numberPicker2 = (android.widget.NumberPicker) findViewById(R.id.numberPicker);

        numberPicker1.getLayoutParams().height=convertDpToPixel(dpHeight/428*100,this);
        numberPicker1.getLayoutParams().width=convertDpToPixel(dpWidth/6,this);
        ViewGroup.MarginLayoutParams p =(ViewGroup.MarginLayoutParams) numberPicker1.getLayoutParams();
        p.setMargins(0,0, convertDpToPixel(dpWidth/336*100,this), 0);
        numberPicker1.requestLayout();
        ViewGroup.MarginLayoutParams p1 =(ViewGroup.MarginLayoutParams) numberPicker2.getLayoutParams();
        p1.setMargins(0,0, convertDpToPixel(dpWidth/327*10,this), 0);
        numberPicker2.requestLayout();


        numberPicker2.getLayoutParams().height=convertDpToPixel(dpHeight/428*100,this);
        numberPicker2.getLayoutParams().width=convertDpToPixel(dpWidth/6,this);


        img = (ImageView) findViewById(R.id.bookthumbnail);
        TextView banner = (TextView) findViewById(R.id.imageview2) ;
        banner.getLayoutParams().height=convertDpToPixel(dpHeight/10,this);

    //    bookrelative = (RelativeLayout)findViewById(R.id.bookrelative) ;
        textView =(TextView) findViewById(R.id.textView);
        textView1=(TextView) findViewById(R.id.textView2);

        textView.getLayoutParams().width=convertDpToPixel(dpWidth/6,this);
        textView.getLayoutParams().height=convertDpToPixel(dpHeight/172*10,this);
        textView1.getLayoutParams().width=convertDpToPixel(dpWidth/6,this);
        textView1.getLayoutParams().height=convertDpToPixel(dpHeight/172*10,this);



        img.getLayoutParams().width = convertDpToPixel(dpWidth/24*10,this);
        img.getLayoutParams().height = convertDpToPixel(dpHeight/317*100,this);


        Intent intent = getIntent();
        final String Title = intent.getExtras().getString("title");
        String Colorim = intent.getExtras().getString("Color");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail") ;
        getSupportActionBar().setTitle(Title);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor(Colorim)));
        img.setImageResource(image);

        final YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtubeplayerview);
        frag.initialize("AIzaSyBP5I5b2XzBcr9yUhOTASffB_Jz1hSxOWQ", this);

        ViewGroup.LayoutParams paramf = frag.getView().getLayoutParams();
        paramf.height = convertDpToPixel(dpHeight/3,this);
        frag.getView().setLayoutParams(paramf);


        FirebaseDatabase db =FirebaseDatabase.getInstance();
        dbref = db.getReference("video");

        System.out.println("beforedb");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    VideoItem videoItem = ds.getValue(VideoItem.class);
                    if(videoItem.getBook().equals(Title))
                    {

                    System.out.println("afterdb");
                    videoItems.add(videoItem);
                    videolinks.add(videoItem.getVideo());
                     }

                    System.out.println("aftervideo");
                }
                youtubelink = videolinks.get(0);

                System.out.println("beforeload");

                System.out.println(youtubelink);
                if (youTubePlayerl!=null) {
                    youTubePlayerl.loadVideo(youtubelink);
                }else{
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            youTubePlayerl.loadVideo(youtubelink);

                        }
                    },1000);

                }
                System.out.println("after handler");


                numberPicker1.setMinValue(1);
                numberPicker2.setMinValue(1);
                numberPicker1.setClickable(false);
                numberPicker2.setClickable(false);

                numberPicker1.setMaxValue(Integer.parseInt(videoItems.get(videoItems.size()-1).getTest()));

                setmaxn2();
                numberPicker1.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(android.widget.NumberPicker numberPicker, int old,int newval) {

                        setmaxn2();
                        for(int i =0 ; i <videoItems.size();i++){
                            System.out.println(newval);
                            if (Integer.parseInt(videoItems.get(i).getTest())==newval && Integer.parseInt(videoItems.get(i).getSual())==numberPicker2.getValue()) {
                                System.out.println(newval);
                                youtubelink = videoItems.get(i).getVideo();
                                System.out.println(youtubelink);
                                youTubePlayerl.loadVideo(youtubelink);

                            }
                        }
                    }
                });
                numberPicker2.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(android.widget.NumberPicker numberPicker, int old,int newval) { for(int i =0 ; i <videoItems.size();i++){
                        if (Integer.parseInt(videoItems.get(i).getSual())==newval && Integer.parseInt(videoItems.get(i).getTest())==numberPicker1.getValue()) {
                            System.out.println(newval);
                            youtubelink = videoItems.get(i).getVideo();
                            System.out.println(youtubelink);
                            youTubePlayerl.loadVideo(youtubelink);

                        }
                    }
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    public void setmaxn2() {
        for (int i = 0; i < videoItems.size(); i++) {
            number2 = Integer.parseInt(videoItems.get(i).getTest());
            if (number2 == numberPicker1.getValue()) {
                int max = Integer.parseInt(videoItems.get(i).getSual());
                if (max > setquestion) {
                    setquestion = max;
                }
            }
        }

        numberPicker2.setMaxValue(setquestion);
        setquestion =1;
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

            if (!b) {
                youTubePlayerl = youTubePlayer;
                setyoutubeplayer = true;

        }
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        System.out.println("failure");

    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return;
    }
}
