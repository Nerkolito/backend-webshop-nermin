package org.example.backendwebshopnermin.model;

/**
 * Representerar kundinformation som behövs för att lägga en beställning.
 */
public class CustomerInfo {
    private String name;
    private String address;
    private String email;

    /**
     * Tom konstruktor – krävs för att Spring Boot ska kunna deserialisera JSON automatiskt.
     */
    public CustomerInfo() {}

    /**
     * Konstruktor med validering.
     * Säkerställer att fälten inte är tomma och att e-post är korrekt formaterad.
     */
    public CustomerInfo(String name, String address, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Namn får inte vara tomt.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Adress får inte vara tom.");
        }
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Ogiltig e-postadress.");
        }
        this.name = name;
        this.address = address;
        this.email = email;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    //  SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
