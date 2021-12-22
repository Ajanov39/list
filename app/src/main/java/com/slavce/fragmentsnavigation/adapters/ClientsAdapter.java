package com.slavce.fragmentsnavigation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.slavce.fragmentsnavigation.R;
import com.slavce.fragmentsnavigation.classes.Client;
import com.slavce.fragmentsnavigation.interfaces.ClientsInterface;

import java.util.ArrayList;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    ArrayList<Client> clients;
    ClientsInterface listener;

    public ClientsAdapter(ArrayList<Client> clients, ClientsInterface listener) {
        this.clients = clients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_client, null);
        ClientsViewHolder viewHolder = new ClientsViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.tvClientName.setText(client.getName());

        holder.tvClientName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.openClientInfo(client);
            }
        });

        holder.tvClientName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.editClientInfo(client);
                return true;
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteClients(client);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }


    public class ClientsViewHolder extends RecyclerView.ViewHolder {

        TextView tvClientName;
        ImageButton btnDelete;

        public ClientsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvClientName = itemView.findViewById(R.id.tvClientName);
            btnDelete = itemView.findViewById(R.id.btnDeleteClient);
        }
    }
}
