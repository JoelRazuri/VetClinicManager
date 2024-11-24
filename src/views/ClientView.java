// package views;


// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// import exceptions.client.ClientNotFoundException;
// import exceptions.client.DuplicateClientException;
// import utils.ScreenUtil;

// import models.Client;

// public class ClientView {
//     // Lista estática para almacenar los clientes (simula la base de datos)
//     private static List<Client> clientList = new ArrayList<>();
    
//     private static Scanner scanner = new Scanner(System.in);

//     public void printMenuClients() {
//         boolean back = false;
        
//         while (!back) {
//             ScreenUtil.clearScreen();
//             System.out.println("\n--- Submenú de Clientes ---");
//             System.out.println("1. Registrar nuevo cliente");
//             System.out.println("2. Mostrar todos los clientes");
//             System.out.println("3. Ver detalle del cliente");
//             System.out.println("4. Modificar cliente");
//             System.out.println("5. Eliminar cliente");
//             System.out.println("6. Volver al menú principal");
//             System.out.print("Seleccione una opción: ");
//             int option = scanner.nextInt();
//             scanner.nextLine();

//             switch (option) {
//                 case 1:
//                     createClient();
//                     waitingMessage();
//                     break;
//                 case 2:
//                     readAllClients();
//                     waitingMessage();
//                 break;
//                 case 3:
//                     readClient();
//                     waitingMessage();
//                     break;
//                 case 4:
//                     updateClient();
//                     waitingMessage();
//                     break;
//                 case 5:
//                     deleteClient();
//                     waitingMessage();
//                     break;
//                 case 6:
//                     back = true;
//                     break;
//                 default:
//                     System.out.println("Opción inválida. Presione Enter para continuar...");
//                     scanner.nextLine();
//                     scanner.nextLine();
//                     break;
//             }
//         }
//     }

//     private static void createClient() {
//         try {
//             System.out.println("\nCrear Cliente");
//             System.out.print("Ingrese el nombre del cliente: ");
//             String name = scanner.nextLine();
//             System.out.print("Ingrese el teléfono del cliente: ");
//             String phone = scanner.nextLine();
//             System.out.print("Ingrese el correo electrónico del cliente: ");
//             String email = scanner.nextLine();

//             Client newClient =  new Client(name, phone, email);
//             if (clientList.contains(newClient)) {
//                 throw new DuplicateClientException("El cliente ya existe.");
//             }
//             clientList.add(newClient);
//             System.out.println("Cliente creado exitosamente.");
//         } catch (DuplicateClientException e) {
//             System.out.println(e.getMessage());
//         }
//     }

//     private static void readAllClients() {
//         System.out.println("\nLista de Clientes:");
//         if (clientList.isEmpty()) {
//             System.out.println("No hay clientes registrados.");
//         } else {
           
//             System.out.printf("%-5s %-20s %-15s %-25s\n", "ID", "Nombre", "Teléfono", "Correo Electrónico");
//             for (Client client : clientList) {
//                 System.out.printf("%-5d %-20s %-15s %-25s\n", 
//                               client.getId(), 
//                               client.getName(), 
//                               client.getPhone(), 
//                               client.getEmail());
//             }
//         }
//     }

//     private static void readClient() {
//         try {
//             System.out.print("\nIngrese el ID del cliente a buscar: ");
//             int clientId = scanner.nextInt();
//             scanner.nextLine(); 

//             Client client = findClientById(clientId);
//             if (client == null) {
//                 throw new ClientNotFoundException("Cliente no encontrado.");
//             }
//             System.out.printf("%-5s %-20s %-15s %-25s\n", "ID", "Nombre", "Teléfono", "Correo Electrónico");
//             System.out.printf("%-5d %-20s %-15s %-25s\n", 
//                               client.getId(), 
//                               client.getName(), 
//                               client.getPhone(), 
//                               client.getEmail());
//         } catch (ClientNotFoundException e) {
//             System.out.println(e.getMessage());
//         }
//     }

//     private static void updateClient() {
//         try {
//             System.out.print("\nIngrese el ID del cliente a actualizar: ");
//             int clientId = scanner.nextInt();
//             scanner.nextLine(); 

//             Client client = findClientById(clientId);
//             if (client == null) {
//                 throw new ClientNotFoundException("Cliente no encontrado.");
//             }

//             System.out.print("Ingrese el nuevo nombre del cliente: ");
//             String name = scanner.nextLine();
//             System.out.print("Ingrese el nuevo teléfono del cliente: ");
//             String phone = scanner.nextLine();
//             System.out.print("Ingrese el nuevo correo electrónico del cliente: ");
//             String email = scanner.nextLine();

//             client.setName(name);
//             client.setPhone(phone);
//             client.setEmail(email);

//             System.out.println("Cliente actualizado exitosamente.");
//         } catch (ClientNotFoundException e) {
//             System.out.println(e.getMessage());
//         }
//     }

//     private static void deleteClient() {
//         try {
//             System.out.print("\nIngrese el ID del cliente a eliminar: ");
//             int clientId = scanner.nextInt();
//             scanner.nextLine(); 

//             Client client = findClientById(clientId);
//             if (client == null) {
//                 throw new ClientNotFoundException("Cliente no encontrado.");
//             }
//             clientList.remove(client);
//             System.out.println("Cliente eliminado exitosamente.");
//         } catch (ClientNotFoundException e) {
//             System.out.println(e.getMessage());
//         }
//     }

//     private static Client findClientById(int clientId) {
//         for (Client client : clientList) {
//             if (client.getId() == clientId) {
//                 return client;
//             }
//         }
//         return null;
//     }

//     public void waitingMessage() {
//         System.out.println("Presione Enter para continuar...");
//         scanner.nextLine();
//     }
// }

package views;

import java.util.Scanner;
import utils.ScreenUtil;

import services.ClientService;
import models.Client;

import java.util.List;

public class ClientView {
    private final ClientService clientService = new ClientService();
    private static final Scanner scanner = new Scanner(System.in);

    public void printMenuClients() {
        boolean back = false;

        while (!back) {
            ScreenUtil.clearScreen();
            System.out.println("\n--- Submenú de Clientes ---");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Mostrar todos los clientes");
            System.out.println("3. Ver detalle del cliente");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createClient();
                    break;
                case 2:
                    readAllClients();
                    break;
                case 3:
                    readClient();
                    break;
                case 4:
                    updateClient();
                    break;
                case 5:
                    deleteClient();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Opción inválida. Presione Enter para continuar...");
                    scanner.nextLine();
                    break;
            }
        }
    }

    private void createClient() {
        System.out.println("\n--- Crear Cliente ---");
        System.out.print("Ingrese el nombre del cliente: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String phone = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico del cliente: ");
        String email = scanner.nextLine();

        Client newClient = new Client(name, phone, email);
        boolean success = clientService.createClient(newClient);

        if (success) {
            System.out.println("Cliente creado exitosamente.");
        } else {
            System.out.println("Error al crear el cliente.");
        }
        waitingMessage();
    }

    private void readAllClients() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Client> clients = clientService.getAllClients();

        if (clients.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.printf("%-5s %-20s %-15s %-25s\n", "ID", "Nombre", "Teléfono", "Correo Electrónico");
            for (Client client : clients) {
                System.out.printf("%-5d %-20s %-15s %-25s\n",
                        client.getId(),
                        client.getName(),
                        client.getPhone(),
                        client.getEmail());
            }
        }
        waitingMessage();
    }

    private void readClient() {
        System.out.println("\n--- Ver Detalle de Cliente ---");
        System.out.print("Ingrese el ID del cliente: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.getClientById(clientId);
        if (client != null) {
            System.out.printf("%-5s %-20s %-15s %-25s\n", "ID", "Nombre", "Teléfono", "Correo Electrónico");
            System.out.printf("%-5d %-20s %-15s %-25s\n",
                    client.getId(),
                    client.getName(),
                    client.getPhone(),
                    client.getEmail());
        } else {
            System.out.println("Cliente no encontrado.");
        }
        waitingMessage();
    }

    private void updateClient() {
        System.out.println("\n--- Modificar Cliente ---");
        System.out.print("Ingrese el ID del cliente: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        Client client = clientService.getClientById(clientId);
        if (client != null) {
            System.out.print("Ingrese el nuevo nombre del cliente: ");
            String name = scanner.nextLine();
            System.out.print("Ingrese el nuevo teléfono del cliente: ");
            String phone = scanner.nextLine();
            System.out.print("Ingrese el nuevo correo electrónico del cliente: ");
            String email = scanner.nextLine();

            client.setName(name);
            client.setPhone(phone);
            client.setEmail(email);

            boolean success = clientService.updateClient(client);
            if (success) {
                System.out.println("Cliente actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el cliente.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
        waitingMessage();
    }

    private void deleteClient() {
        System.out.println("\n--- Eliminar Cliente ---");
        System.out.print("Ingrese el ID del cliente: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        boolean success = clientService.deleteClient(clientId);
        if (success) {
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el cliente.");
        }
        waitingMessage();
    }

    private void waitingMessage() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
