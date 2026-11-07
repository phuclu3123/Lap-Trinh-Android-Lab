package vn.edu.ueh.phuclu.buoi7_btvn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnLoad;
    List<Article> articleList = new ArrayList<Article>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        articleList.add(new Article(
                1,
                "RecyclerView trong Android",
                "Hiển thị danh sách dữ liệu bằng RecyclerView, Adapter và ViewHolder.",
                R.drawable.article_recyclerview,
                10
        ));
        articleList.add(new Article(
                2,
                "Activity và Intent",
                "Truyền dữ liệu giữa các màn hình bằng Intent.",
                R.drawable.article_intent,
                12
        ));
        articleList.add(new Article(
                3,
                "ViewHolder Pattern",
                "Tái sử dụng View khi hiển thị danh sách trong RecyclerView.",
                R.drawable.article_viewholder,
                8
        ));
        articleList.add(new Article(
                4,
                "Thiết kế giao diện Android",
                "Sử dụng layout và các View cơ bản để xây dựng giao diện.",
                R.drawable.article_ui,
                15
        ));
        articleList.add(new Article(
                5,
                "Xử lý sự kiện OnClick",
                "Bắt sự kiện nhấn vào một phần tử trong danh sách.",
                R.drawable.article_onclick,
                6
        ));

        recyclerView = findViewById(R.id.RecyclerView);
        btnLoad = findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter myAdapter = new MyAdapter(v.getContext(), articleList);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(myAdapter);
            }
        });
    }
}
