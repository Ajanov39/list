package com.slavce.fragmentsnavigation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.slavce.fragmentsnavigation.R;
import com.slavce.fragmentsnavigation.adapters.ClientsAdapter;
import com.slavce.fragmentsnavigation.classes.Client;
import com.slavce.fragmentsnavigation.interfaces.ClientsInterface;
import com.slavce.fragmentsnavigation.util.AppHolder;
import com.slavce.fragmentsnavigation.util.Constants;


public class ClientsFragment extends Fragment implements ClientsInterface {

    RecyclerView rvClients;
    FloatingActionButton btnAddClient;
    ClientsAdapter adapter;


    public ClientsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvClients = view.findViewById(R.id.rvClients);
        btnAddClient = view.findViewById(R.id.fabAddClient);

        adapter = new ClientsAdapter(AppHolder.clients, this);

        rvClients.setAdapter(adapter);

        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, new AddClientFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });


    }

    @Override
    public void deleteClients(Client client) {
        AppHolder.clients.remove(client);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openClientInfo(Client client) {
//        ClientInfoFragment fragment = ClientInfoFragment.newInstance(client);

        Bundle extras = new Bundle();
        extras.putParcelable(Constants.EXTRA_CLIENT, client);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, ClientInfoFragment.class, extras)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void editClientInfo(Client client) {
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.EXTRA_CLIENT, client);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, UpdateClientInfoFragment.class, extras)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        rvClients.setAdapter(null);
        adapter = new ClientsAdapter(AppHolder.clients, this);
        rvClients.setAdapter(adapter);
    }
}