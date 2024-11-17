import java.util.Scanner;
import utils.ScreenUtil;

import views.ClientView;
import controllers.ClientController;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        ClientController clientController = new ClientController();
        ClientView clientView = new ClientView(clientController);


        boolean exit = false;

        while (!exit){
            ScreenUtil.clearScreen();
            System.out.println("\n --- VETCLINIC MANGAGER ---");
            System.out.println("*************************");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Mascotas");
            System.out.println("3. Gestión de Turnos");
            System.out.println("4. Gestión de Historias Clínicas");
            System.out.println("5. Gestión de Inventario");
            System.out.println("6. Facturación");
            System.out.println("7. Salir");

            System.out.println("Por favor dígite la opción:");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    clientView.printMenuClients();
                    break;
                case 2:
                    // petView.printMenuPets();
                    break;
                case 3:
                    // medicalShiftView.printMenuMedicalShifts();
                    break;
                case 4:
                    // medicalShiftView.printMenuMedicalHistories();
                    break;
                case 5:
                    // inventoryView.printMenuInventory();
                    break;
                case 6:
                    // billView.printMenuBill();
                    break;
                case 7:
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
