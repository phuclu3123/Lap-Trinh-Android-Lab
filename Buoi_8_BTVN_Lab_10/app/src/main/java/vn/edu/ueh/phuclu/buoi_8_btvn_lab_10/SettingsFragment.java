package vn.edu.ueh.phuclu.buoi_8_btvn_lab_10;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        configureTextInput("pref_name", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        configureTextInput("pref_age", InputType.TYPE_CLASS_NUMBER);
        configureTextInput("pref_birthday", InputType.TYPE_CLASS_TEXT);
        configureTextInput("pref_email", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        configureTextInput("pref_phone", InputType.TYPE_CLASS_PHONE);

        Preference agePreference = findPreference("pref_age");
        if (agePreference != null) {
            agePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    try {
                        int age = Integer.parseInt(String.valueOf(newValue));
                        if (age < 1 || age > 120) {
                            Toast.makeText(requireContext(), "Tuổi phải từ 1 đến 120", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        return true;
                    } catch (NumberFormatException e) {
                        Toast.makeText(requireContext(), "Tuổi phải là số hợp lệ", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            });
        }
    }

    private void configureTextInput(String key, final int inputType) {
        EditTextPreference preference = findPreference(key);
        if (preference != null) {
            preference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(EditText editText) {
                    editText.setInputType(inputType);
                    editText.setSelection(editText.getText().length());
                }
            });
        }
    }
}
