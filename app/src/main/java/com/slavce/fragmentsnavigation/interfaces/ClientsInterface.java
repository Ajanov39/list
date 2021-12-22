package com.slavce.fragmentsnavigation.interfaces;

import com.slavce.fragmentsnavigation.classes.Client;

public interface ClientsInterface {
    public void deleteClients(Client client);
    public void openClientInfo(Client client);

   public void editClientInfo(Client client);
}
