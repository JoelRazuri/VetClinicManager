// package models;


// public class Client {
//     // Atributos
//     private static int idCounter = 1;
//     private int id;
//     public String name;
//     protected String phone;
//     protected String email;

//     // Constructor
//     public Client(String name, String phone, String email) {
//         this.id = idCounter++;
//         this.name = name;
//         this.phone = phone;
//         this. email = email;
//     }

//     // Getters y Setters
//     public int getId() {
//         return id;
//     }

//     public void setId(int id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getPhone() {
//         return phone;
//     }

//     public void setPhone(String phone) {
//         this.phone = phone;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

// }

package models;

public class Client {
    // Atributos
    private int id;
    private String name;
    private String phone;
    private String email;

    // Constructor para nuevos clientes (sin ID)
    public Client(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Constructor para clientes con ID (desde la base de datos)
    public Client(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para representación fácil
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
