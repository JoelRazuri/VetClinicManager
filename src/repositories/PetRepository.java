package repositories;

import config.DatabaseConfig;
import models.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    // Guardar una mascota en la base de datos
    public void save(Pet pet) throws Exception {
        String sql = "INSERT INTO pet (name, species, race, gender, age, id_client) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSpecies());
            statement.setString(3, pet.getRace());
            statement.setString(4, pet.getGender());
            statement.setInt(5, pet.getAge());
            statement.setInt(6, pet.getIdClient());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar mascota: " + e.getMessage());
            throw e;
        }
    }

    // Obtener todas las mascotas
    public List<Pet> findAll() throws Exception {
        String sql = "SELECT * FROM pet";
        List<Pet> pets = new ArrayList<>();
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Pet pet = new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        resultSet.getString("race"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getInt("id_client")
                );
                pets.add(pet);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mascotas: " + e.getMessage());
            throw e;
        }
        return pets;
    }

    // Buscar mascota por ID
    public Pet findById(int id) throws Exception {
        String sql = "SELECT * FROM pet WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Pet(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("species"),
                            resultSet.getString("race"),
                            resultSet.getString("gender"),
                            resultSet.getInt("age"),
                            resultSet.getInt("id_client")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener mascota por ID: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // Actualizar una mascota
    public void update(Pet pet) throws Exception {
        String sql = "UPDATE pet SET name = ?, species = ?, race = ?, gender = ?, age = ?, id_client = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSpecies());
            statement.setString(3, pet.getRace());
            statement.setString(4, pet.getGender());
            statement.setInt(5, pet.getAge());
            statement.setInt(6, pet.getIdClient());
            statement.setInt(7, pet.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar mascota: " + e.getMessage());
            throw e;
        }
    }

    // Eliminar una mascota por ID
    public boolean deleteById(int id) throws Exception {
        String sql = "DELETE FROM pet WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            throw e;
        }
    }

    // Verificar si existe una mascota por ID del dueño
    public boolean existsByClientId(int idClient) throws Exception {
        String sql = "SELECT COUNT(*) FROM pet WHERE id_client = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idClient);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de mascota por ID del dueño: " + e.getMessage());
            throw e;
        }
        return false;
    }
}
