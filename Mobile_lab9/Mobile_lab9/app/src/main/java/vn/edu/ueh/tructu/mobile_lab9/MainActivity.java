package vn.edu.ueh.tructu.mobile_lab9;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTitle("Setting");

        tvProfile = findViewById(R.id.tv_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Settings
        if (item.getItemId() == R.id.menu_settings) {

            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );

            startActivityForResult(intent, 100);

            return true;
        }

        // About
        if (item.getItemId() == R.id.menu_about) {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("About");

            builder.setMessage(
                    "Thông tin cá nhân\n\n" +
                            "Họ tên: Lữ Võ Hoàng Phúc\n" +
                            "MSSV: 3124105389\n" +
                            "Lớp: SE0001\n" +
                            "Ngành: Kỹ thuật phần mềm\n" +
                            "Trường: Đại học Kinh tế TP.HCM"
            );

            builder.setPositiveButton("OK", null);

            builder.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == 100 && resultCode == RESULT_OK) {

            if (data == null) {
                return;
            }

            String gender =
                    data.getStringExtra("gender");

            String age =
                    data.getStringExtra("age");

            String username =
                    data.getStringExtra("username");

            String email =
                    data.getStringExtra("email");

            String phone =
                    data.getStringExtra("phone");

            String birthday =
                    data.getStringExtra("birthday");

            String language =
                    data.getStringExtra("language");

            String notification =
                    data.getStringExtra("notification");

            String profile =
                    "Profile Information\n\n" +
                            "Gender: " + gender + "\n" +
                            "Age: " + age + "\n" +
                            "Username: " + username + "\n" +
                            "Email: " + email + "\n" +
                            "Phone: " + phone + "\n" +
                            "Birthday: " + birthday + "\n" +
                            "Language: " + language + "\n" +
                            "Notifications: " + notification;

            tvProfile.setText(profile);
        }
    }
}