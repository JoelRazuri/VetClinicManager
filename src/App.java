
import java.util.Scanner;
import utils.ScreenUtil;

import views.ClientView;
import views.PetView;
import views.MedicalShiftView;


public class App {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        ClientView clientView = new ClientView();
        PetView petView = new PetView();
        MedicalShiftView medicalShiftView = new MedicalShiftView();

        boolean exit = false;

        while (!exit){
            ScreenUtil.clearScreen();
            System.out.println("\n --- VETCLINIC MANGAGER ---");
            System.out.println("*************************");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Mascotas");
            System.out.println("3. Gestión de Turnos");
            System.out.println("4. Gestión de Historias Clínicas");
            System.out.println("5. Salir");

            System.out.println("Por favor dígite la opción:");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    clientView.printMenuClients();
                    break;
                case 2:
                    petView.printMenuPets();
                    break;
                case 3:
                    medicalShiftView.printMenuMedicalShifts();
                    break;
                case 4:
                    // medicalShiftView.printMenuMedicalHistories();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción inválida. Presione Enter para continuar...");
                    scanner.nextLine();
                    scanner.nextLine();
                    break;
            }
        }
    }

}
