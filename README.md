# Webshop Backend – Nermin

Ett backend-API byggt i **Java med Spring Boot** som hanterar produkter och kundordrar i en webshop. Systemet använder **in-memory-lagring** för enkel testning och utveckling.

---

## 📦 Funktionalitet

- ✅ Hämta alla produkter
- 🔍 Hämta en produkt via ID
- 🛒 Skapa order (inkl. kunduppgifter och flera produkter)
- 📃 Se alla ordrar
- 🔍 Se enskild order via ID
- 🧾 Filtrera ordrar via kundnamn
- ❌ Radera order och återställ lagersaldo

---

## 🛠️ Teknologier

| Teknologi      | Version |
|----------------|---------|
| Java           | 17+     |
| Spring Boot    | 3.x     |
| Maven/Gradle   | valfritt |
| Postman        | För test av API |
| JUnit          | 5       |

---

## ▶️ Kom igång

### 1. Klona projektet
```bash
git clone https://github.com/ditt-repo/webshop-backend.git
cd webshop-backend
```

### 2. Kör applikationen
```bash
./mvnw spring-boot:run
```

**Servern startar på:**  
`http://localhost:8080`

---

## 📬 Exempel på API-anrop

### 🔹 Hämta alla produkter
```
GET /api/products
```

### 🔹 Hämta produkt med ID 1
```
GET /api/products/1
```

### 🔹 Skapa en order
```
POST /api/orders
Content-Type: application/json

{
  "customerInfo": {
    "name": "Kalle Karlsson",
    "address": "Gatan 1",
    "email": "kalle@example.com"
  },
  "items": [
    { "productId": 1, "quantity": 2 },
    { "productId": 2, "quantity": 1 }
  ]
}
```

### 🔹 Hämta order med ID
```
GET /api/orders/{orderId}
```

### 🔹 Hämta alla ordrar för kund
```
GET /api/orders/customer/Kalle Karlsson
```

### 🔹 Radera en order
```
DELETE /api/orders/{orderId}
```

---

## 🧱 Projektstruktur

```
src/
├── controller/         # RestController-klasser för API
├── service/            # Affärslogik
├── repository/         # In-memory datalager (HashMaps)
├── model/              # Domänklasser: Product, Order, CustomerInfo etc.
├── exception/          # Anpassade exceptions + global handler
├── dto/                # DTO:er för API-svar
└── test/               # Enhetstester med JUnit
```

---

## ✅ Tester

Projektet innehåller enhetstester för:

- `OrderService`
- `ProductService`

Kör med:
```bash
./mvnw test
```

---

## 📌 Övrigt

- All validering sker både i modeller och controller.
- Alla fel returnerar tydliga JSON-fel via `GlobalExceptionHandler`.


