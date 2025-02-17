package com.disco.smstowebhook;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsFragment extends PreferenceFragmentCompat {


    private MultiSelectListPreference multiSelectPreference = findPreference("number");
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        multiSelectPreference = findPreference("number");

        Preference addNumberPreference = findPreference("number_add");

        if (addNumberPreference != null) {
            // Add custom button to add items
            addNumberPreference.setOnPreferenceClickListener(preference -> {
                showAddItemDialog();
                return true;
            });
        }
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("번호 추가");

        // Input field for new item
        final EditText input = new EditText(requireContext());
        builder.setView(input);

        builder.setPositiveButton("추가", (dialog, which) -> {
            String newItem = input.getText().toString();
            if (!newItem.isEmpty()) {
                addItemToPreference(newItem);
            }
        });
        builder.setNegativeButton("취소", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void addItemToPreference(String newItem) {
        // Get current entries and values
        CharSequence[] entries = multiSelectPreference.getEntries();
        CharSequence[] entryValues = multiSelectPreference.getEntryValues();

        // Add the new item
        List<CharSequence> newEntries = new ArrayList<>(Arrays.asList(entries));
        List<CharSequence> newEntryValues = new ArrayList<>(Arrays.asList(entryValues));

        newEntries.add(newItem);
        newEntryValues.add(newItem);

        // Update the preference
        multiSelectPreference.setEntries(newEntries.toArray(new CharSequence[0]));
        multiSelectPreference.setEntryValues(newEntryValues.toArray(new CharSequence[0]));
    }
}