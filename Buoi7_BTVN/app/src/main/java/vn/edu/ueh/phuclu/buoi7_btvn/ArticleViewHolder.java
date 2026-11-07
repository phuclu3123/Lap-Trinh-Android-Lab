package vn.edu.ueh.phuclu.buoi7_btvn;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private MyAdapter mAdapter;
    public TextView tid;
    public TextView ttitle;
    public TextView tdesc;
    public TextView tviews;
    public ImageView imageArticle;

    public ArticleViewHolder(@NonNull View item, MyAdapter adapter) {
        super(item);
        this.mAdapter = adapter;
        this.tid = item.findViewById(R.id.tid);
        this.ttitle = item.findViewById(R.id.ttitle);
        this.tdesc = item.findViewById(R.id.tdesc);
        this.tviews = item.findViewById(R.id.tviews);
        this.imageArticle = item.findViewById(R.id.imageArticle);
        item.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getBindingAdapterPosition();

        if (position == RecyclerView.NO_POSITION) {
            return;
        }

        Article article = mAdapter.getArticle(position);
        article.increaseViews();
        mAdapter.notifyItemChanged(position);

        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        intent.putExtra("ARTICLE_ID", article.getId());
        intent.putExtra("ARTICLE_TITLE", article.getTitle());
        intent.putExtra("ARTICLE_DESC", article.getShortDescription());
        intent.putExtra("ARTICLE_IMAGE", article.getImageResource());
        intent.putExtra("ARTICLE_VIEWS", article.getViews());
        v.getContext().startActivity(intent);
    }
}
