package vn.edu.ueh.phuclu.buoi7_btvn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> articleList;
    private LayoutInflater mInflater;
    public MyAdapter(Context context, List<Article> list) {
        mInflater = LayoutInflater.from(context);
        this.articleList = list;
    }


    // Tạo giao diện 1 item
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = mInflater.inflate(R.layout.article_layout, parent, false);
        return new ArticleViewHolder(item, this);
    }

    // Đổ dữ liệu vào item
    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.tid.setText("ID: " + article.getId());
        holder.ttitle.setText(article.getTitle());
        holder.tdesc.setText(article.getShortDescription());
        holder.tviews.setText("Views: " + article.getViews());
        holder.imageArticle.setImageResource(article.getImageResource());
    }

    // Có bao nhiêu item
    @Override
    public int getItemCount() {
        return articleList.size();
    }
    public Article getArticle(int position) {
        return articleList.get(position);
    }
}
