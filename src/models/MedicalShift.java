package models;

import java.sql.Date;

public class MedicalShift {
    // Atributos
    private static int idCounter = 1;
    private int id;
    public Date date;
    public String description;
    public String treatment;
    public String vaccines;
    private int idPet;

    // Constructor
    public MedicalShift(Date date, String description, String treatment, String vaccines, int idPet) {
        this.id = idCounter++;
        this.date = date;
        this.description = description;
        this.treatment = treatment;
        this.vaccines = vaccines;
        this.idPet = idPet;
    }

     // Getters y Setters
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getVaccines() {
        return vaccines;
    }

    public void setVaccines(String vaccines) {
        this.vaccines = vaccines;
    }

    public int getPetId() {
        return idPet;
    }

    public void setPetId(int idPet) {
        this.idPet = idPet;
    }
}
