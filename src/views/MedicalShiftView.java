package views;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import models.MedicalShift;
import utils.ScreenUtil;

public class MedicalShiftView {
     // Lista estática para almacenar los clientes (simula la base de datos)
    private static List<MedicalShift> medicalShiftList = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);

    public void printMenuMedicalShifts() {
        boolean back = false;
        
        while (!back) {
            ScreenUtil.clearScreen();
            System.out.println("\n--- Submenú de Turnos ---");
            System.out.println("1. Registrar nuevo turno");
            System.out.println("2. Mostrar todos los turnos");
            System.out.println("3. Ver detalle del turno");
            System.out.println("4. Modificar turno");
            System.out.println("5. Eliminar turno");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createMedicalShift();
                    waitingMessage();
                    break;
                case 2:
                    readAllMedicalShifts();
                    waitingMessage();
                break;
                case 3:
                    readMedicalShift();
                    waitingMessage();
                    break;
                case 4:
                    updateMedicalShift();
                    waitingMessage();
                    break;
                case 5:
                    deleteMedicalShift();
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

    private static void createMedicalShift() {
        try {
            System.out.println("\n--- Registrar Turno Médico ---");
    
            System.out.print("Fecha (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            Date date = Date.valueOf(dateInput); // Convertir a Date.
    
            System.out.print("Descripción: ");
            String description = scanner.nextLine();
    
            System.out.print("Tratamiento: ");
            String treatment = scanner.nextLine();
            
            System.out.print("Vacunas: ");
            String vaccines = scanner.nextLine();

            System.out.print("ID de la Mascota: ");
            int petId = Integer.parseInt(scanner.nextLine());
    
            MedicalShift medicalShift = new MedicalShift(date, description, treatment, vaccines, petId);
            medicalShiftList.add(medicalShift);
    
            System.out.println("Turno médico registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Fecha inválida. Use el formato YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void readAllMedicalShifts() {
        System.out.println("\nLista de Turnos Médicos:");
        if (medicalShiftList.isEmpty()) {
            System.out.println("No hay turnos registrados.");
        } else {
            System.out.printf("%-5s %-15s %-30s %-20s %-10s\n", 
                              "ID", "Fecha", "Descripción", "Tratamiento", "Vacunas","ID Mascota");
            for (MedicalShift shift : medicalShiftList) {
                System.out.printf("%-5d %-15s %-30s %-20s %-10d\n", 
                                  shift.getId(), 
                                  shift.getDate().toString(), 
                                  shift.getDescription(), 
                                  shift.getTreatment(), 
                                  shift.getVaccines(), 
                                  shift.getPetId());
            }
        }
    }
    
    private static void readMedicalShift() {
        try {
            System.out.println("\n--- Consultar Turno Médico ---");
            System.out.print("Ingrese el ID del Turno Médico: ");
            int id = Integer.parseInt(scanner.nextLine());
    
            MedicalShift shift = findMedicalShiftById(id);
            if (shift != null) {
                System.out.println("Detalles del Turno Médico:");
                System.out.printf("ID: %d, Fecha: %s, Descripción: %s, Tratamiento: %s, Vacunas: %s,ID Mascota: %d\n",
                                  shift.getId(), 
                                  shift.getDate().toString(), 
                                  shift.getDescription(), 
                                  shift.getTreatment(), 
                                  shift.getVaccines(), 
                                  shift.getPetId());
            } else {
                System.out.println("No se encontró un turno médico con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para el ID.");
        }
    }
    
    private static void updateMedicalShift() throws NumberFormatException {
        try {
            System.out.println("\n--- Actualizar Turno Médico ---");
            System.out.print("Ingrese el ID del Turno Médico: ");
            int id = Integer.parseInt(scanner.nextLine());
    
            MedicalShift shift = findMedicalShiftById(id);
            if (shift != null) {
                System.out.print("Nueva Fecha (actual: " + shift.getDate() + "): ");
                String dateInput = scanner.nextLine();
                if (!dateInput.isEmpty()) {
                    shift.setDate(Date.valueOf(dateInput));
                }
    
                System.out.print("Nueva Descripción (actual: " + shift.getDescription() + "): ");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    shift.setDescription(description);
                }
    
                System.out.print("Nuevo Tratamiento (actual: " + shift.getTreatment() + "): ");
                String treatment = scanner.nextLine();
                if (!treatment.isEmpty()) {
                    shift.setTreatment(treatment);
                }

                System.out.print("Nueva Vacuna (actual: " + shift.getVaccines() + "): ");
                String vaccines = scanner.nextLine();
                if (!vaccines.isEmpty()) {
                    shift.setVaccines(vaccines);
                }
    
                System.out.println("Turno médico actualizado exitosamente.");
            } else {
                System.out.println("No se encontró un turno médico con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Fecha inválida. Use el formato YYYY-MM-DD.");
        }
    }
    
    private static void deleteMedicalShift() {
        try {
            System.out.println("\n--- Eliminar Turno Médico ---");
            System.out.print("Ingrese el ID del Turno Médico: ");
            int id = Integer.parseInt(scanner.nextLine());
    
            MedicalShift shift = findMedicalShiftById(id);
            if (shift != null) {
                medicalShiftList.remove(shift);
                System.out.println("Turno médico eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un turno médico con el ID proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido para el ID.");
        }
    }
    
    private static MedicalShift findMedicalShiftById(int id) {
        for (MedicalShift shift : medicalShiftList) {
            if (shift.getId() == id) {
                return shift;
            }
        }
        return null;
    }

    public void waitingMessage() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
