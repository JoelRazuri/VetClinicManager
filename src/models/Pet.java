package models;

public class Pet {
    // Atributos
    private static int idCounter = 1;
    private int id;
    public String name;
    public String species;
    public String race;
    public String gender;
    public int age;
    private int idClient;

    // Constructor
    public Pet(String name, String species, String race, String gender, int age, int idClient) {
        this.id = idCounter;
        this.name = name;
        this.species = species;
        this.race = race;
        this.gender = gender;
        this.age = age;
        this.idClient = idClient;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

}
