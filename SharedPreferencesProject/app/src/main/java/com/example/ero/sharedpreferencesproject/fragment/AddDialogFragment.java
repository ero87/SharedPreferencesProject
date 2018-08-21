package com.example.ero.sharedpreferencesproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ero.sharedpreferencesproject.models.Model;
import com.example.ero.sharedpreferencesproject.R;
import com.example.ero.sharedpreferencesproject.activitys.MainActivity;

import java.util.Objects;

public class AddDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_dialog_fragment, container, false);
        final EditText editKey = view.findViewById(R.id.edit_key);
        final EditText editValue = view.findViewById(R.id.edit_value);
        final Button save = view.findViewById(R.id.button_id);

        saveClick(editKey, editValue, save);
        return view;
    }

    private void saveClick(final EditText editKey, final EditText editValue, Button save) {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Model model = new Model(editKey.getText().toString(), editValue.getText().toString());
                ((MainActivity) Objects.requireNonNull(getActivity())).getList().add(model);
                dismiss();
            }
        });
    }
}
