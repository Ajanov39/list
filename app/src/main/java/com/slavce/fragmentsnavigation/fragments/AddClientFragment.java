package com.slavce.fragmentsnavigation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.slavce.fragmentsnavigation.R;
import com.slavce.fragmentsnavigation.classes.Client;
import com.slavce.fragmentsnavigation.util.AppHolder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientFragment extends Fragment {

    EditText etName, etAddress, etDescription;
    Button btnAddCLient;



    public AddClientFragment() {

    }


    public static AddClientFragment newInstance(String param1, String param2) {
        AddClientFragment fragment = new AddClientFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_add_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etAddress = view.findViewById(R.id.etClientAddress);
        etName = view.findViewById(R.id.etClientName);
        etDescription = view.findViewById(R.id.etClientDescription);
        btnAddCLient = view.findViewById(R.id.btnAddClient);

        enableButton(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(checkFieldsEmpty()) {
                    enableButton(true);
                } else {
                    enableButton(false);
                }
            }
        };

        etName.addTextChangedListener(textWatcher);
        etAddress.addTextChangedListener(textWatcher);
        etDescription.addTextChangedListener(textWatcher);

        btnAddCLient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client client = new Client(etName.getText().toString(),
                        etDescription.getText().toString(),
                        etAddress.getText().toString());

                AppHolder.clients.add(client);

                requireActivity().onBackPressed();

            }
        });

    }

    public Boolean checkFieldsEmpty() {
        if(etName.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty() ||
        etDescription.getText().toString().isEmpty()) {
            return false;
        } else  {
            return true;
        }
    }

    public void enableButton(Boolean isEnabled) {
        btnAddCLient.setEnabled(isEnabled);
    }

}