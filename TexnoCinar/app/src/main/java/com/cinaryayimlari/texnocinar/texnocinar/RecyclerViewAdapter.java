package com.cinaryayimlari.texnocinar.texnocinar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by User on 24.03.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

    private Context context;
    private List<Book> books;
    private final int mDefaultSpanCount;
    private String colors[] = {"#94c965","#dc704e","#f0c243","#69bcca"};
    private int i =0;

    @Override
    public int getItemViewType(int position) {
       // return books.get(position).getCategory()== Book.Header? 0 : 1 ;
        if(books.get(position).getCategory()==Book.Header){
            return 0;
        }
        else if(books.get(position).getCategory()==Book.Card){
            return 1;
        }
        else {
            return 2;
        }
    }


    public RecyclerViewAdapter(Context context,  List<Book> books ,GridLayoutManager gridLayoutManager, int defaultSpanCount) {
        this.context = context;
        this.books = books;
        mDefaultSpanCount =defaultSpanCount;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return isHeaderType(position) ? mDefaultSpanCount : 1;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType ==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title,parent,false);
        }else if(viewType ==1){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item,parent,false);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_exam,parent,false);

        }
        return new ViewHolder(view);
    }
    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = Math.round( dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(books.get(position).getCategory()==Book.Header){

            TextView title = (TextView) holder.itemView.findViewById(R.id.texttitle);

           // title.setTextSize(10 * context.getResources().getDisplayMetrics().density);
            LinearLayout linearLayout = (LinearLayout) holder.itemView.findViewById(R.id.linearlay1);
            title.setText(books.get(position).getTitle());


            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);
            float density  = context.getResources().getDisplayMetrics().density;
            float dpHeight = outMetrics.heightPixels / density;
            float dpWidth  = outMetrics.widthPixels / density;
            float heightf = dpHeight/1006*100;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    convertDpToPixel(heightf,context)

            );
            linearLayout.setLayoutParams(params);



         //   System.out.println(books.get(position).getTitle());

        }else if(books.get(position).getCategory()==Book.Card) {

            System.out.println(books.get(position).getTitle());
            TextView title2;
            ImageView thumbnail;
            CardView cardView;

            title2 = (TextView) holder.itemView.findViewById(R.id.book_title_id);
            thumbnail =(ImageView)holder.itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) holder.itemView.findViewById(R.id.cardview);


            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);
            float density  = context.getResources().getDisplayMetrics().density;
            float dpHeight = outMetrics.heightPixels / density;
            float dpWidth  = outMetrics.widthPixels / density;
            float widthf = dpWidth/24*10;
            float heightf = dpHeight/3;

            CardView.LayoutParams params = new CardView.LayoutParams(
                    convertDpToPixel(widthf,context),
                    convertDpToPixel(heightf,context)
            );
            cardView.setLayoutParams(params);

            thumbnail.getLayoutParams().height=convertDpToPixel(dpHeight/38*10,context)  ;
            int a =Math.round( 20 * context.getResources().getDisplayMetrics().density);

         //   title2.setTextSize(TypedValue.COMPLEX_UNIT_SP,6 * context.getResources().getDisplayMetrics().density);
            System.out.println(a + "a");

            title2.setText(books.get(position).getTitle());
            thumbnail.setImageResource(books.get(position).getThumbnail());
//            thumbnail.setBackgroundColor(Color.parseColor(colors[i]));
            final String s = colors[i];
            if(i==3){
                i=0;
            }else{
            i++;
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,BookItemActivity.class);
                    intent.putExtra("title",books.get(position).getTitle());
                    intent.putExtra("Description",books.get(position).getDescription());
                    intent.putExtra("Thumbnail",books.get(position).getThumbnail());
                    intent.putExtra("Color",s);
                    System.out.println("asdasd");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);


                }
            });
        }
        else{

            TextView title2;
            ImageView thumbnail;
            CardView cardView;

            title2 = (TextView) holder.itemView.findViewById(R.id.book_title_id1);
            thumbnail =(ImageView)holder.itemView.findViewById(R.id.book_img_id1);
            cardView = (CardView) holder.itemView.findViewById(R.id.cardview1);

            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics ();
            display.getMetrics(outMetrics);
            float density  = context.getResources().getDisplayMetrics().density;
            float dpHeight = outMetrics.heightPixels / density;
            float dpWidth  = outMetrics.widthPixels / density;
            float widthf = dpWidth/18*10;
            float heightf = dpHeight/2;

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    convertDpToPixel(widthf,context),
                    convertDpToPixel(heightf,context)
            );
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            cardView.setLayoutParams(params);
            thumbnail.getLayoutParams().height=convertDpToPixel(dpHeight/251*100,context)  ;
            title2.setText(books.get(position).getTitle());
            thumbnail.setImageResource(books.get(position).getThumbnail());
//            thumbnail.setBackgroundColor(Color.parseColor(colors[i]));
            final String s = colors[i];
            if(i==3){
                i=0;
            }else{
                i++;
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,BookItemActivity.class);
                    intent.putExtra("title",books.get(position).getTitle());
                    intent.putExtra("Description",books.get(position).getDescription());
                    intent.putExtra("Thumbnail",books.get(position).getThumbnail());
                    intent.putExtra("Color",s);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);


                }
            });


        }
    }
    private boolean isHeaderType(int position) {
       // return books.get(position).getCategory() == Book.Header ? true : false;
        if(books.get(position).getCategory()==Book.Header){
            return true;
        }
        else if(books.get(position).getCategory()==Book.Card){
            return false;
        }
        else {
            return true;
        }
    }


    @Override
    public int getItemCount() {
        return books.size();
    }
}
