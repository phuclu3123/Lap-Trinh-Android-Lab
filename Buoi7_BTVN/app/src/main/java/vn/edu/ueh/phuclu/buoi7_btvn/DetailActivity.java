package vn.edu.ueh.phuclu.buoi7_btvn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailMain), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        ImageView imageDetail = findViewById(R.id.imageDetail);
        TextView txtId = findViewById(R.id.txtDetailId);
        TextView txtTitle = findViewById(R.id.txtDetailTitle);
        TextView txtDesc = findViewById(R.id.txtDetailDesc);
        TextView txtViews = findViewById(R.id.txtDetailViews);

        int articleId = getIntent().getIntExtra("ARTICLE_ID", 0);
        String articleTitle = getIntent().getStringExtra("ARTICLE_TITLE");
        String articleDesc = getIntent().getStringExtra("ARTICLE_DESC");
        int articleImage = getIntent().getIntExtra("ARTICLE_IMAGE", R.drawable.article_recyclerview);
        int articleViews = getIntent().getIntExtra("ARTICLE_VIEWS", 0);

        txtId.setText("ID: " + articleId);
        txtTitle.setText(articleTitle);
        txtDesc.setText(articleDesc);
        txtViews.setText("Views: " + articleViews);
        imageDetail.setImageResource(articleImage);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
