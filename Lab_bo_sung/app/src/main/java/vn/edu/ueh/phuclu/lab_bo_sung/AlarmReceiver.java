package vn.edu.ueh.phuclu.lab_bo_sung;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    // CLass này kế thừa BroadcastReceiver

    public static final String EXTRA_ID = "notification_id";
    public static final String EXTRA_EVENT = "event";
    public static final String EXTRA_CONTENT = "content";
    private static final String CHANNEL_ID = "alarm_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Nhận dữ liệu từ MainActivity
        int id = intent.getIntExtra(EXTRA_ID, -1);
        String event = intent.getStringExtra(EXTRA_EVENT);
        String content = intent.getStringExtra(EXTRA_CONTENT);
        if (id < 0 || event == null || content == null) return;

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Theo slide Notification Channels: API 26+ bắt buộc có channel.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Tạo Notification Channel
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Alarm Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager.createNotificationChannel(channel);
        }

        // Read More mở riêng DetailActivity, mang theo ID/Event/Content.
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(EXTRA_ID, id);
        detailIntent.putExtra(EXTRA_EVENT, event);
        detailIntent.putExtra(EXTRA_CONTENT, content);

        // Tạo pendingIntent
        PendingIntent detailPendingIntent = PendingIntent.getActivity(
                context,
                id,
                detailIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Tạo nội dung Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(event)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(detailPendingIntent)
                .addAction(android.R.drawable.ic_menu_view, "Read More", detailPendingIntent)
                .setAutoCancel(false); // Chỉ xóa khi nhấn Clear hoặc tự vuốt bỏ.

        if (Build.VERSION.SDK_INT >= 33 &&
                context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // ID khác nhau => notification mới, KHÔNG ghi đè cái trước. -> gửi lên hệ thống Android
        manager.notify(id, builder.build());
    }
}

