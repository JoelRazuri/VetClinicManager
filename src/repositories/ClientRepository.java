// package repositories;

// public class ClientRepository {
    
// }

package repositories;

import config.DatabaseConfig;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

    // Guardar un cliente en la base de datos
    public void save(Client client) throws Exception {
        String sql = "INSERT INTO client (name, phone, email) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            throw e;  
        }
    }

    // Obtener todos los clientes
    public List<Client> findAll() throws Exception {
        String sql = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
            throw e;  
        }
        return clients;
    }

    // Buscar cliente por ID
    public Client findById(int id) throws Exception {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();  
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
            throw e;  
        }
        return null;
    }

    // Actualizar un cliente
    public void update(Client client) throws Exception {
        String sql = "UPDATE client SET name = ?, phone = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();  
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getPhone());
            statement.setString(3, client.getEmail());
            statement.setInt(4, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            throw e; 
        }
    }

    // Eliminar un cliente por ID
    public boolean deleteById(int id) throws Exception {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();  
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            throw e;  
        }
    }

    // Verificar si existe un cliente con un email dado
    public boolean existsByEmail(String email) throws Exception {
        String sql = "SELECT COUNT(*) FROM client WHERE email = ?";
        try (Connection connection = DatabaseConfig.getConnection();  
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de cliente por email: " + e.getMessage());
            throw e;  
        }
        return false;
    }
}
