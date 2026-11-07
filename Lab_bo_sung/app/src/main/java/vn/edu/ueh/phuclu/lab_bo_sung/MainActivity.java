package vn.edu.ueh.phuclu.lab_bo_sung;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Các form nhập dữ liệu.
    private EditText edtTime, edtEvent, edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Maping các form nhập dữ liệu.
        edtTime = findViewById(R.id.edtTime);
        edtEvent = findViewById(R.id.edtEvent);
        edtContent = findViewById(R.id.edtContent);
        Button btnSetAlarm = findViewById(R.id.btnSetAlarm);
        Button btnCancel = findViewById(R.id.btnCancel);

        // Android 13+: xin quyền hiện thông báo.
        if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
        }

        // Chạy function setAlarm khi nhấn nút Set Alarm.
        btnSetAlarm.setOnClickListener(v -> setAlarm());
        // Cancel ở màn hình nhập chỉ xóa form, không hủy các alarm đã đặt.
        btnCancel.setOnClickListener(v -> clearForm());
    }

    private void setAlarm() {
        // Lấy dữ liệu từ các form nhập.
        String timeText = edtTime.getText().toString().trim();
        String event = edtEvent.getText().toString().trim();
        String content = edtContent.getText().toString().trim();

        // Kiểm tra các thông tin bị trống.
        if (timeText.isEmpty() || event.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ Time, Event, Content", Toast.LENGTH_SHORT).show();
            return;
        }

        int seconds;
        try {
            seconds = Integer.parseInt(timeText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Time phải là số giây hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (seconds <= 0) {
            Toast.makeText(this, "Time phải lớn hơn 0", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Build.VERSION.SDK_INT >= 33 &&
                checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Hãy cấp quyền thông báo rồi thử lại", Toast.LENGTH_LONG).show();
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
            return;
        }

        // Tạo ID riêng cho MỖI alarm. ID này cũng là notification ID.
        /*
        Nguyên lý: Mở vùng lưu trữ => đọc id cũ => tạo id mới (tăng 1 đơn vị) => lưu id mới
        // 1. Mở vùng lưu trữ
        SharedPreferences prefs =
                getSharedPreferences("alarms", MODE_PRIVATE);

        // 2. Đọc ID cũ
        int lastId = prefs.getInt("last_id", 0);

        // 3. Tạo ID mới
        int id = lastId + 1;

        // 4. Lưu ID mới
        prefs.edit().putInt("last_id", id).apply();
        */
        int id = getSharedPreferences("alarms", MODE_PRIVATE)
                .getInt("last_id", 0) + 1;
        getSharedPreferences("alarms", MODE_PRIVATE)
                .edit().putInt("last_id", id).apply();

        // Tạo intent cho AlarmReceiver.
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra(AlarmReceiver.EXTRA_ID, id);
        intent.putExtra(AlarmReceiver.EXTRA_EVENT, event);
        intent.putExtra(AlarmReceiver.EXTRA_CONTENT, content);

        // Tạo pendingIntent cho AlarmReceiver.
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Đặt alarm
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long triggerTime = SystemClock.elapsedRealtime() + seconds * 1000L;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME, triggerTime, pendingIntent);

        Toast.makeText(this, "Đã hẹn thông báo sau " + seconds + " giây", Toast.LENGTH_LONG).show();
        clearForm();
    }

    // Hủy các alarm đã đặt nếu nhấn nút clear
    private void clearForm() {
        edtTime.setText("");
        edtEvent.setText("");
        edtContent.setText("");
    }
}
