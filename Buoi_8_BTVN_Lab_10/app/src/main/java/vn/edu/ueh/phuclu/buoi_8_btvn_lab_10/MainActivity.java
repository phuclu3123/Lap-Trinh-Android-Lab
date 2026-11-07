package vn.edu.ueh.phuclu.buoi_8_btvn_lab_10;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private TextView tvProfileName;
    private TextView tvAvatar;
    private LinearLayout containerProfileItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Khởi tạo giá trị mặc định từ xml/preferences.xml ở lần chạy đầu tiên vào SharedPreferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        tvProfileName = findViewById(R.id.tv_profile_name);
        tvAvatar = findViewById(R.id.tv_avatar);
        containerProfileItems = findViewById(R.id.container_profile_items);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProfileFromSettings();
    }

    private void showProfileFromSettings() {
        // Đọc dữ liệu từ SharedPreferences (được đồng bộ từ PreferenceFragmentCompat trong Settings)
        String name = getTextSetting("pref_name", "Lữ Võ Hoàng Phúc");
        String gender = getGenderLabel(sharedPreferences.getString("pref_gender", "male"));
        String age = getTextSetting("pref_age", "22");
        String birthday = getTextSetting("pref_birthday", "01/01/2004");
        String email = getTextSetting("pref_email", "phuc@example.com");
        String phone = getTextSetting("pref_phone", "0900 000 000");
        String city = getTextSetting("pref_city", "TP. Hồ Chí Minh");
        boolean isPublic = sharedPreferences.getBoolean("pref_public", true);
        String publicValue = getString(isPublic ? R.string.profile_public_value : R.string.profile_private_value);

        tvProfileName.setText(name);
        tvAvatar.setText(getInitial(name));

        // Xóa các views cũ và nạp động (inflate) từng dòng thông tin từ SharedPreferences
        containerProfileItems.removeAllViews();

        addSettingRow(getString(R.string.pref_name_title), name, getString(R.string.desc_name));
        addSettingRow(getString(R.string.pref_gender_title), gender, getString(R.string.desc_gender));
        addSettingRow(getString(R.string.pref_age_title), age, getString(R.string.desc_age));
        addSettingRow(getString(R.string.pref_birthday_title), birthday, getString(R.string.desc_birthday));
        addSettingRow(getString(R.string.pref_email_title), email, getString(R.string.desc_email));
        addSettingRow(getString(R.string.pref_phone_title), phone, getString(R.string.desc_phone));
        addSettingRow(getString(R.string.pref_city_title), city, getString(R.string.desc_city));
        addSettingRow(getString(R.string.pref_public_title), publicValue, getString(R.string.desc_public));
    }

    private String getTextSetting(String key, String fallback) {
        String value = sharedPreferences.getString(key, fallback);
        if (value == null || value.trim().isEmpty()) {
            return getString(R.string.not_updated);
        }
        return value;
    }

    private void addSettingRow(final String title, final String value, final String description) {
        // Sinh layout item động từ file item_profile_setting.xml bằng Java code
        View rowView = LayoutInflater.from(this).inflate(R.layout.item_profile_setting, containerProfileItems, false);
        TextView tvTitle = rowView.findViewById(R.id.tv_setting_title);
        TextView tvValue = rowView.findViewById(R.id.tv_setting_value);

        tvTitle.setText(title);
        tvValue.setText(value);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileDetailActivity.class);
                intent.putExtra(ProfileDetailActivity.EXTRA_TITLE, title);
                intent.putExtra(ProfileDetailActivity.EXTRA_VALUE, value);
                intent.putExtra(ProfileDetailActivity.EXTRA_DESCRIPTION, description);
                startActivity(intent);
            }
        });

        containerProfileItems.addView(rowView);
    }

    private String getGenderLabel(String value) {
        if ("female".equals(value)) return "Nữ";
        if ("other".equals(value)) return "Khác";
        return "Nam";
    }

    private String getInitial(String name) {
        if (name == null || name.trim().isEmpty()) return "P";
        return name.trim().substring(0, 1).toUpperCase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_about) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("About");
            builder.setMessage(
                    "Thông tin sinh viên\n\n" +
                            "Họ tên: Lữ Võ Hoàng Phúc\n" +
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
}
