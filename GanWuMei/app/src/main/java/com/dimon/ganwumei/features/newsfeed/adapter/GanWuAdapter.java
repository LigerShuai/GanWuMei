package com.dimon.ganwumei.features.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entitys.News;
import com.dimon.ganwumei.features.newsfeed.activity.NewsActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dimon on 2016/3/11.
 */
public class GanWuAdapter extends RecyclerView.Adapter<GanWuAdapter.NewsViewHolder>{

    private List<News> newses;
    @Inject Context context;

    public GanWuAdapter(List<News> newses,Context context) {
        this.newses = newses;
        this.context=context;
    }


    //自定义ViewHolder类
    static class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView news_photo;
        TextView news_title;
        TextView news_desc;
        Button share;
        Button readMore;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            news_photo= (ImageView) itemView.findViewById(R.id.news_photo);
            news_title= (TextView) itemView.findViewById(R.id.news_title);
            news_desc= (TextView) itemView.findViewById(R.id.news_desc);
            share= (Button) itemView.findViewById(R.id.btn_share);
            readMore= (Button) itemView.findViewById(R.id.btn_more);
            //设置TextView背景为半透明
            news_title.setBackgroundColor(Color.argb(20, 0, 0, 0));

        }


    }
    @Override
    public GanWuAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_ganhuo,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(GanWuAdapter.NewsViewHolder personViewHolder, int i) {
        final int j=i;

        personViewHolder.news_photo.setImageResource(newses.get(i).getPhotoId());
        personViewHolder.news_title.setText(newses.get(i).getTitle());
        personViewHolder.news_desc.setText(newses.get(i).getDesc());

        //为btn_share btn_readMore cardView设置点击事件
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsActivity.class);
                intent.putExtra("News",newses.get(j));
                context.startActivity(intent);
            }
        });

        personViewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, newses.get(j).getDesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(intent, newses.get(j).getTitle()));
            }
        });

        personViewHolder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsActivity.class);
                intent.putExtra("News",newses.get(j));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newses.size();
    }
}