package vn.edu.ueh.phuclu.buoi_8_btvn_lab_10;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class ProfileDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "profile_title";
    public static final String EXTRA_VALUE = "profile_value";
    public static final String EXTRA_DESCRIPTION = "profile_description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.detail_title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvLabel = findViewById(R.id.tv_detail_label);
        TextView tvValue = findViewById(R.id.tv_detail_value);
        TextView tvDescription = findViewById(R.id.tv_detail_description);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String value = getIntent().getStringExtra(EXTRA_VALUE);
        String description = getIntent().getStringExtra(EXTRA_DESCRIPTION);

        tvLabel.setText(title == null ? "" : title);
        tvValue.setText(value == null ? getString(R.string.not_updated) : value);
        tvDescription.setText(description == null ? "" : description);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
