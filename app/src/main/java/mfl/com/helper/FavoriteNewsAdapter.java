package mfl.com.helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import mfl.com.R;
import mfl.com.databinding.FavoriteNewsItemModelBinding;
import mfl.com.databinding.NewsItemModelBinding;
import mfl.com.db.news.NewsEntity;
import mfl.com.pojo.news.NewNewsList;
import mfl.com.ui.home.fragment.news.details.NewsDetailsScreen;

public class FavoriteNewsAdapter extends RecyclerView.Adapter<FavoriteNewsAdapter.ViewHolder> {
    private static final String TAG = FavoriteNewsAdapter.class.getSimpleName();

    public FavoriteNewsItemModelBinding binding;
    private Context context;
    private List<NewsEntity> newNewsLists;

    public FavoriteNewsAdapter(Context context, List<NewsEntity> newNewsLists) {
        this.context = context;
        this.newNewsLists = newNewsLists;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.favorite_news_item_model, parent, false
        );
        return new FavoriteNewsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsEntity model = newNewsLists.get(position);

        Picasso.get().load(model.getNewsImg()).into(holder.binding.newsImg, new Callback() {
            @Override
            public void onSuccess() {
                holder.binding.progressLoadPhoto.setVisibility(View.GONE);
                holder.binding.newsImg.setScaleType(ImageView.ScaleType.FIT_XY);
            }

            @Override
            public void onError(Exception e) {
                holder.binding.progressLoadPhoto.setVisibility(View.GONE);
                holder.binding.newsImg.setImageResource(android.R.color.darker_gray);
                holder.binding.newsImg.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });


        holder.binding.newsTitle.setText(model.getNewsTitle());
        holder.binding.newsDescription.setText(model.getNewsDescription());

        holder.binding.newsDate.setText(model.getNewsDate());


        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailsScreen.class);
                intent.putExtra("newsId", String.valueOf(model.getNewsId()));
                intent.putExtra("newsDescription", String.valueOf(model.getNewsDescription()));
                intent.putExtra("createdBy", String.valueOf(model.getCreatedBy()));
                intent.putExtra("newsDate", String.valueOf(model.getNewsDate()));
                intent.putExtra("newsImg", String.valueOf(model.getNewsImg()));
                intent.putExtra("newsTitle", String.valueOf(model.getNewsTitle()));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newNewsLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public FavoriteNewsItemModelBinding binding;

        public ViewHolder(FavoriteNewsItemModelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
