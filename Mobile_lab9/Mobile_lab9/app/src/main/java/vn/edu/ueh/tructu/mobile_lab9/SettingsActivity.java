package vn.edu.ueh.tructu.mobile_lab9;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch swGender;
    Switch swNotification;

    EditText edtAge;
    EditText edtUsername;
    EditText edtEmail;
    EditText edtPhone;
    EditText edtBirthday;

    Spinner spLanguage;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Profile");

        // ScrollView
        ScrollView scrollView = new ScrollView(this);

        // Layout chính
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 20);

        scrollView.addView(layout);

        // =========================
        // TIÊU ĐỀ
        // =========================

        TextView tvTitle = new TextView(this);
        tvTitle.setText("Profile Settings");
        tvTitle.setTextSize(24);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        titleParams.setMargins(0, 0, 0, 20);

        layout.addView(tvTitle, titleParams);

        // =========================
        // 1. GENDER
        // =========================

        TextView tvGender = new TextView(this);
        tvGender.setText("Gender");
        tvGender.setTextSize(16);
        tvGender.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvGender);

        swGender = new Switch(this);
        swGender.setText("Male");
        swGender.setChecked(true);

        LinearLayout.LayoutParams genderParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        genderParams.setMargins(0, 0, 0, 15);

        layout.addView(swGender, genderParams);

        // =========================
        // 2. AGE
        // =========================

        TextView tvAge = new TextView(this);
        tvAge.setText("Age");
        tvAge.setTextSize(16);
        tvAge.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvAge);

        edtAge = new EditText(this);
        edtAge.setHint("Enter age");
        edtAge.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout.LayoutParams ageParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        ageParams.setMargins(0, 0, 0, 15);

        layout.addView(edtAge, ageParams);

        // =========================
        // 3. USERNAME
        // =========================

        TextView tvUsername = new TextView(this);
        tvUsername.setText("Username");
        tvUsername.setTextSize(16);
        tvUsername.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvUsername);

        edtUsername = new EditText(this);
        edtUsername.setHint("Enter username");
        edtUsername.setInputType(InputType.TYPE_CLASS_TEXT);

        LinearLayout.LayoutParams usernameParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        usernameParams.setMargins(0, 0, 0, 15);

        layout.addView(edtUsername, usernameParams);

        // =========================
        // 4. EMAIL
        // =========================

        TextView tvEmail = new TextView(this);
        tvEmail.setText("Email");
        tvEmail.setTextSize(16);
        tvEmail.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvEmail);

        edtEmail = new EditText(this);
        edtEmail.setHint("Enter email");
        edtEmail.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        LinearLayout.LayoutParams emailParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        emailParams.setMargins(0, 0, 0, 15);

        layout.addView(edtEmail, emailParams);

        // =========================
        // 5. PHONE
        // =========================

        TextView tvPhone = new TextView(this);
        tvPhone.setText("Phone");
        tvPhone.setTextSize(16);
        tvPhone.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvPhone);

        edtPhone = new EditText(this);
        edtPhone.setHint("Enter phone");
        edtPhone.setInputType(InputType.TYPE_CLASS_PHONE);

        LinearLayout.LayoutParams phoneParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        phoneParams.setMargins(0, 0, 0, 15);

        layout.addView(edtPhone, phoneParams);

        // =========================
        // 6. BIRTHDAY
        // =========================

        TextView tvBirthday = new TextView(this);
        tvBirthday.setText("Birthday");
        tvBirthday.setTextSize(16);
        tvBirthday.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvBirthday);

        edtBirthday = new EditText(this);
        edtBirthday.setHint("dd/mm/yyyy");
        edtBirthday.setInputType(InputType.TYPE_CLASS_DATETIME);

        LinearLayout.LayoutParams birthdayParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        birthdayParams.setMargins(0, 0, 0, 15);

        layout.addView(edtBirthday, birthdayParams);

        // =========================
        // 7. LANGUAGE
        // =========================

        TextView tvLanguage = new TextView(this);
        tvLanguage.setText("Language");
        tvLanguage.setTextSize(16);
        tvLanguage.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvLanguage);

        spLanguage = new Spinner(this);

        String[] languages = {
                "Vietnamese",
                "English",
                "Japanese",
                "Korean"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        languages
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spLanguage.setAdapter(adapter);

        LinearLayout.LayoutParams languageParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        languageParams.setMargins(0, 0, 0, 15);

        layout.addView(spLanguage, languageParams);

        // =========================
        // 8. NOTIFICATIONS
        // =========================

        TextView tvNotification = new TextView(this);
        tvNotification.setText("Notifications");
        tvNotification.setTextSize(16);
        tvNotification.setTypeface(null, android.graphics.Typeface.BOLD);

        layout.addView(tvNotification);

        swNotification = new Switch(this);
        swNotification.setText("Enable notifications");
        swNotification.setChecked(true);

        LinearLayout.LayoutParams notificationParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        notificationParams.setMargins(0, 0, 0, 25);

        layout.addView(swNotification, notificationParams);

        // =========================
        // BUTTON SAVE
        // =========================

        btnSave = new Button(this);
        btnSave.setText("SAVE");

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        layout.addView(btnSave, buttonParams);

        // Hiển thị giao diện
        setContentView(scrollView);

        // =========================
        // XỬ LÝ NÚT SAVE
        // =========================

        btnSave.setOnClickListener(v -> {

            String gender;

            if (swGender.isChecked()) {
                gender = "Male";
            } else {
                gender = "Female";
            }

            String notification;

            if (swNotification.isChecked()) {
                notification = "Enabled";
            } else {
                notification = "Disabled";
            }

            String language =
                    spLanguage.getSelectedItem().toString();

            Intent result = new Intent();

            result.putExtra("gender", gender);
            result.putExtra(
                    "age",
                    edtAge.getText().toString()
            );
            result.putExtra(
                    "username",
                    edtUsername.getText().toString()
            );
            result.putExtra(
                    "email",
                    edtEmail.getText().toString()
            );
            result.putExtra(
                    "phone",
                    edtPhone.getText().toString()
            );
            result.putExtra(
                    "birthday",
                    edtBirthday.getText().toString()
            );
            result.putExtra("language", language);
            result.putExtra("notification", notification);

            setResult(RESULT_OK, result);

            finish();
        });
    }
}