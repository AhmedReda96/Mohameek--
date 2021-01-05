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
import mfl.com.databinding.NewsItemModelBinding;
import mfl.com.pojo.news.NewNewsList;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.fragment.news.details.NewsDetailsScreen;
import mfl.com.ui.start.signUp.SignUpScreenVM;

public class NewNewsAdapter extends RecyclerView.Adapter<NewNewsAdapter.ViewHolder> {
    private static final String TAG = NewNewsAdapter.class.getSimpleName();

    public NewsItemModelBinding binding;
    private Context context;
    private List<NewNewsList> newNewsLists;
    private GeneralMethods generalMethods;

    public NewNewsAdapter(Context context, List<NewNewsList> newNewsLists) {
        this.context = context;
        this.newNewsLists = newNewsLists;
        generalMethods=new GeneralMethods(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.news_item_model, parent, false
        );
        return new NewNewsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewNewsList model = newNewsLists.get(position);

        Picasso.get().load(model.getPhoto()).into(holder.binding.newsImg, new Callback() {
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


        holder.binding.newsTitle.setText(model.getTitle());
        holder.binding.newsDescription.setText(model.getDescription());

        holder.binding.newsDate.setText(generalMethods.getDate(model.getCreatedAt()));


        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailsScreen.class);
                intent.putExtra("newsId", String.valueOf(model.getId()));
                intent.putExtra("newsDescription", String.valueOf(model.getDescription()));
                intent.putExtra("createdBy", String.valueOf(model.getCreatedBy()));
                intent.putExtra("newsDate", String.valueOf(generalMethods.getDate(model.getCreatedAt())));
                intent.putExtra("newsImg", String.valueOf(model.getPhoto()));
                intent.putExtra("newsTitle", String.valueOf(model.getTitle()));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return newNewsLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public NewsItemModelBinding binding;

        public ViewHolder(NewsItemModelBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }



}
