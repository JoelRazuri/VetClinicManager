package services;

import models.Pet;
import repositories.PetRepository;

import java.util.List;

public class PetService {
    private final PetRepository petRepository = new PetRepository();

    // Crear una mascota
    public boolean createPet(Pet pet) {
        try {
            petRepository.save(pet);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Obtener todas las mascotas
    public List<Pet> getAllPets() {
        try {
            return petRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Obtener una mascota por ID
    public Pet getPetById(int id) {
        try {
            return petRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Actualizar una mascota
    public boolean updatePet(Pet pet) {
        Pet existingPet = null;
        try {
            existingPet = petRepository.findById(pet.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (existingPet == null) {
            return false;
        }
        try {
            petRepository.update(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // Eliminar una mascota por ID
    public boolean deletePet(int id) {
        try {
            return petRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
