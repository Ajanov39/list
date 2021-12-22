package com.slavce.fragmentsnavigation.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.slavce.fragmentsnavigation.R;

public class LoginFragment extends Fragment {

    EditText etUsername, etPassword;
    Button btnLogin;

    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnLogin = view.findViewById(R.id.btnLogin);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);

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
                if(checkFields()) {
                    enableButton(true);
                } else {
                    enableButton(false);
                }
            }
        };

        etPassword.addTextChangedListener(textWatcher);
        etUsername.addTextChangedListener(textWatcher);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etUsername.getText().toString().equals("User12") &&
                etPassword.getText().toString().equals("Test123!")) {
                    requireActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, new ClientsFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
                    dialog
                            .setTitle("Wrong Credentials")
                            .setMessage("Please insert the correct credentials, " +
                                    "or contact your administrator")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                }
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }

    public Boolean checkFields() {
        if(etUsername.getText().toString().length() > 3 &&
                etPassword.getText().toString().length() > 3) {
            return true;
        } else {
            return false;
        }
    }

    public void enableButton(Boolean isEnabled) {
        btnLogin.setEnabled(isEnabled);
    }
}