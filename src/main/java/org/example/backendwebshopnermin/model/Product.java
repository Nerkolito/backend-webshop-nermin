package org.example.backendwebshopnermin.model;

/**
 * Representerar en produkt i webshoppen.
 * Innehåller grundläggande information som används i katalog och ordrar.
 */
public class Product {

    private Long id;             // Unikt ID för produkten
    private String name;         // Namn på produkten
    private String description;  // Kort beskrivning
    private double price;        // Pris i kronor
    private String imageUrl;     // Länk till produktbild
    private int stock;           // Antal produkter i lager

    /**
     * Tom konstruktor krävs av JSON/Spring deserialisering.
     */
    public Product() {}

    /**
     * Fullständig konstruktor med validering av pris och lagersaldo.
     */
    public Product(Long id, String name, String description, double price, String imageUrl, int stock) {
        validatePrice(price);
        validateStock(stock);

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    //  GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }

    /**
     * Sätter nytt pris, men tillåter inte negativa värden.
     */
    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getStock() { return stock; }

    /**
     * Uppdaterar stock – men inte till ett negativt värde.
     */
    public void setStock(int stock) {
        validateStock(stock);
        this.stock = stock;
    }


    /**
     *  Säkerställer att priset inte är negativt.
     */
    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Pris får inte vara negativt.");
        }
    }

    /**
     *  Säkerställer att stock inte är negativt.
     */
    private void validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Lagersaldo får inte vara negativt.");
        }
    }
}
