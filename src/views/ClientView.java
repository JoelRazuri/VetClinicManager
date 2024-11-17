package views;

import controllers.ClientController;
import java.util.Scanner;
import utils.ScreenUtil;

public class ClientView {
    private ClientController clientController;
    private Scanner scanner;

    public ClientView(ClientController clientController) {
        this.clientController = clientController;  
        this.scanner = new Scanner(System.in);
    }

    public void printMenuClients() {
        boolean back = false;
        
        while (!back) {
            ScreenUtil.clearScreen();
            System.out.println("\n--- Submenú de Clientes ---");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Ver detalles de cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // clientController.addClient();
                    break;
                case 2:
                    // clientController.printClientInfo();
                break;
                case 3:
                    // clientController.updateClient();
                    break;
                case 4:
                    // clientController.deleteClient();
                    break;
                case 5:
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
}
