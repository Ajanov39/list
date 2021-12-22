package com.slavce.fragmentsnavigation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.slavce.fragmentsnavigation.R;
import com.slavce.fragmentsnavigation.classes.Client;
import com.slavce.fragmentsnavigation.util.Constants;


public class ClientInfoFragment extends Fragment {

    TextView tvClientName, tvClientAddress, tvClientDescription;
    private Client client;
    Button btnEdit;

    public ClientInfoFragment() {

    }


    public static ClientInfoFragment newInstance(Client client) {
        ClientInfoFragment fragment = new ClientInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_CLIENT, client);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            client = getArguments().getParcelable(Constants.EXTRA_CLIENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvClientName = view.findViewById(R.id.tvClientName);
        tvClientAddress = view.findViewById(R.id.tvClientAddress);
        tvClientDescription = view.findViewById(R.id.tvClientDescription);

        if(client != null) {
            tvClientName.setText(client.getName());
            tvClientAddress.setText(client.getAddress());
            tvClientDescription.setText(client.getDescription());
        }


    }
}