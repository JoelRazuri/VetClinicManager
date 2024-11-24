package services;

import models.Client;
import repositories.ClientRepository;

import java.util.List;

public class ClientService {
    private final ClientRepository clientRepository = new ClientRepository();

    public boolean createClient(Client client) {
        try {
            if (clientRepository.existsByEmail(client.getEmail())) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Client> getAllClients()  {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
                return null;
    }

    public Client getClientById(int id)  {
        try {
            return clientRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
                return null;
    }

    public boolean updateClient(Client client) {
        Client existingClient = null;
        try {
            existingClient = clientRepository.findById(client.getId());
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        if (existingClient == null) {
            return false;
        }
        try {
            clientRepository.update(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteClient(int id) {
        try {
            return clientRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
                return false;
    }
}
