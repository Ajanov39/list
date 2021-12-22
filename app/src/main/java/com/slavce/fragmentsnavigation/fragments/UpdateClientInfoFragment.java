package com.slavce.fragmentsnavigation.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.slavce.fragmentsnavigation.R;
import com.slavce.fragmentsnavigation.adapters.ClientsAdapter;
import com.slavce.fragmentsnavigation.classes.Client;
import com.slavce.fragmentsnavigation.util.AppHolder;
import com.slavce.fragmentsnavigation.util.Constants;

public class UpdateClientInfoFragment extends Fragment {




    EditText etClientName, etClientAddress, etClientDescription;
    private Client client;
    Button btnDone;
    ClientsAdapter adapter;
    RecyclerView rvClients;

    public UpdateClientInfoFragment() {

    }


    public static UpdateClientInfoFragment newInstance(Client client) {
        UpdateClientInfoFragment fragment = new UpdateClientInfoFragment();
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
        return inflater.inflate(R.layout.fragment_update_client_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etClientName = view.findViewById(R.id.etClientName);
        etClientAddress = view.findViewById(R.id.etClientAddress);
        etClientDescription = view.findViewById(R.id.etClientDescription);
        btnDone = view.findViewById(R.id.btnDone);
        rvClients = view.findViewById(R.id.rvClients);



        if(client != null) {
            etClientName.setHint(client.getName());
            etClientAddress.setHint(client.getAddress());
            etClientDescription.setHint(client.getDescription());
        }


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        etClientName.addTextChangedListener(textWatcher);
        etClientAddress.addTextChangedListener(textWatcher);
        etClientDescription.addTextChangedListener(textWatcher);

//        btnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Client client = new Client(etClientName.getText().toString(),
//                        etClientDescription.getText().toString(),
//                        etClientAddress.getText().toString());
//
//                adapter.notifyDataSetChanged();
//
//                requireActivity().onBackPressed();
//
//            }
//        });

    }
}