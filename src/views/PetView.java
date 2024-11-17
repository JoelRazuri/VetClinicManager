package views;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Pet;
import utils.ScreenUtil;

public class PetView {
    // Lista estática para almacenar los clientes (simula la base de datos)
    private static List<Pet> petList = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);

    public void printMenuPets() {
        boolean back = false;
        
        while (!back) {
            ScreenUtil.clearScreen();
            System.out.println("\n--- Submenú de Mascotas ---");
            System.out.println("1. Registrar nueva mascota");
            System.out.println("2. Mostrar todas las mascotas");
            System.out.println("3. Ver detalle de mascota");
            System.out.println("4. Modificar mascota");
            System.out.println("5. Eliminar mascota");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createPet();
                    waitingMessage();
                    break;
                case 2:
                    readAllPets();
                    waitingMessage();
                break;
                case 3:
                    readPet();
                    waitingMessage();
                    break;
                case 4:
                    updatePet();
                    waitingMessage();
                    break;
                case 5:
                    deletePet();
                    waitingMessage();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida. Presione Enter para continuar...");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
            }
        }
    }

    private static void createPet() {
        try {
            System.out.println("\n--- Registrar Mascota ---");

            System.out.print("Nombre: ");
            String name = scanner.nextLine();

            System.out.print("Especie: ");
            String species = scanner.nextLine();

            System.out.print("Raza: ");
            String race = scanner.nextLine();

            System.out.print("Género: ");
            String gender = scanner.nextLine();

            System.out.print("Edad: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("ID del Dueño: ");
            int idClient = Integer.parseInt(scanner.nextLine());

            Pet pet = new Pet(name, species, race, gender, age, idClient);
            petList.add(pet);
            System.out.println("Mascota registrada exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para los campos de ID o edad.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void readAllPets() {
        System.out.println("\nLista de Mascotas:");
        if (petList.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-15s %-10s %-5s %-10s\n", 
                            "ID", "Nombre", "Especie", "Raza", "Género", "Edad", "ID Dueño");
            for (Pet pet : petList) {
                System.out.printf("%-5d %-20s %-15s %-15s %-10s %-5d %-10d\n", 
                                pet.getId(), 
                                pet.getName(), 
                                pet.getSpecies(), 
                                pet.getRace(), 
                                pet.getGender(), 
                                pet.getAge(), 
                                pet.getIdClient());
            }
        }
    }

    private static void readPet() {
        try {
            System.out.println("\n--- Consultar Mascota ---");
            System.out.print("Ingrese el ID de la Mascota: ");
            int id = Integer.parseInt(scanner.nextLine());

            Pet pet = findPetById(id);
            if (pet != null) {
                System.out.println("Detalles de la Mascota:");
                System.out.printf("ID: %d, Nombre: %s, Especie: %s, Raza: %s, Género: %s, Edad: %d, ID Dueño: %d\n",
                                pet.getId(), pet.getName(), pet.getSpecies(), pet.getRace(), 
                                pet.getGender(), pet.getAge(), pet.getIdClient());
            } else {
                System.out.println("No se encontró una mascota con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para el ID.");
        }
    }

    private static void updatePet() {
        try {
            System.out.println("\n--- Actualizar Mascota ---");
            System.out.print("Ingrese el ID de la Mascota: ");
            int id = Integer.parseInt(scanner.nextLine());

            Pet pet = findPetById(id);
            if (pet != null) {
                System.out.print("Nuevo Nombre (actual: " + pet.getName() + "): ");
                pet.setName(scanner.nextLine());

                System.out.print("Nueva Especie (actual: " + pet.getSpecies() + "): ");
                pet.setSpecies(scanner.nextLine());

                System.out.print("Nueva Raza (actual: " + pet.getRace() + "): ");
                pet.setRace(scanner.nextLine());

                System.out.print("Nuevo Género (actual: " + pet.getGender() + "): ");
                pet.setGender(scanner.nextLine());

                System.out.print("Nueva Edad (actual: " + pet.getAge() + "): ");
                pet.setAge(Integer.parseInt(scanner.nextLine()));

                System.out.println("Mascota actualizada exitosamente.");
            } else {
                System.out.println("No se encontró una mascota con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para los campos de ID o edad.");
        }
    }

    private static void deletePet() {
        try {
            System.out.println("\n--- Eliminar Mascota ---");
            System.out.print("Ingrese el ID de la Mascota: ");
            int id = Integer.parseInt(scanner.nextLine());

            Pet pet = findPetById(id);
            if (pet != null) {
                petList.remove(pet);
                System.out.println("Mascota eliminada exitosamente.");
            } else {
                System.out.println("No se encontró una mascota con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para el ID.");
        }
    }

    private static Pet findPetById(int id) {
        for (Pet pet : petList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public void waitingMessage() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
