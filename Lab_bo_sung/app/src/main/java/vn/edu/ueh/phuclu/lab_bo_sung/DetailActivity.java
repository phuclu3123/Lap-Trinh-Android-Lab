package vn.edu.ueh.phuclu.lab_bo_sung;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    // Readmore & clear
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView txtEvent = findViewById(R.id.txtEvent);
        TextView txtContent = findViewById(R.id.txtContent);
        Button btnClear = findViewById(R.id.btnClear);

        // Lấy dữ liệu từ intent
        int id = getIntent().getIntExtra(AlarmReceiver.EXTRA_ID, -1);
        String event = getIntent().getStringExtra(AlarmReceiver.EXTRA_EVENT);
        String content = getIntent().getStringExtra(AlarmReceiver.EXTRA_CONTENT);

        // Sau đó hiển thị lên màn hình
        txtEvent.setText(event == null ? "" : event);
        txtContent.setText(content == null ? "" : content);

        // Xử lý nút clear thông báo
        btnClear.setOnClickListener(v -> {
            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (id != -1) {
                manager.cancel(id); // Chỉ xóa đúng thông báo này.
            }
            finish();
        });
    }
}
