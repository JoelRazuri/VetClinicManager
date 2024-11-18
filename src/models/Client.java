package models;


public class Client {
    // Atributos
    private static int idCounter = 1;
    private int id;
    public String name;
    protected String phone;
    protected String email;

    // Constructor
    public Client(String name, String phone, String email) {
        this.id = idCounter++;
        this.name = name;
        this.phone = phone;
        this. email = email;
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

}
