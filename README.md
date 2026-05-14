# 💳 Digital Banking System (Spring Boot)

A full-stack banking backend system built using **Java, Spring Boot, and JPA**, designed with scalable architecture and real-world BFS (Banking & Financial Services) concepts.

---

## 🚀 Features

### 🏦 Account Management
- Create account
- Fetch account details
- View all accounts

### 💸 Transaction Management
- Money transfer between accounts
- Balance validation (Insufficient funds handling)
- Atomic transactions using `@Transactional`
- Transaction history tracking

### 📊 Excel Reporting
- Download **Account data as Excel**
- Download **Transaction history as Excel**
- Dynamic column mapping using **Enum + Functional programming**

### ⚠️ Exception Handling
- Global exception handling (`@RestControllerAdvice`)
- Structured error response (message, status, path)

### 📘 API Documentation
- Integrated with **Swagger UI**

---

## 🧠 Architecture

The project follows a **layered architecture**:
Controller → Service → Repository → Entity

### ✅ Layers:
- **Controller** → Handles HTTP requests
- **Service** → Business logic
- **Repository** → Database operations (JPA)
- **Entity** → Database models

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (for development)
- Apache POI (Excel generation)
- Swagger (API documentation)
- Maven

---

## 📂 Project Structure


com.banking.app
├── account
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── enums
├── transaction
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── enums
├── common
│   └── service (Excel generator)
└── exception

---

## 🔥 Key Highlights

✅ Functional programming using `Function<T, R>`  
✅ Clean Excel export architecture (no reflection)  
✅ Reusable Excel generator service  
✅ Proper REST API design using `ResponseEntity`  
✅ Global exception handling  
✅ Transaction safety with `@Transactional`  

---

## 📥 API Endpoints

### 🏦 Account APIs

POST   /accounts
GET    /accounts/{id}
GET    /accounts
GET    /accounts/excel

---

### 💸 Transaction APIs

POST   /transactions/transfer
GET    /transactions/history/{accountId}
GET    /transactions/history/excel/{accountId}

---

## 📊 Example Excel Output

### Account Excel:
| ID | Name | Balance |
|----|------|---------|

### Transaction Excel:
| ID | From | To | Amount | Timestamp |

---

## 🧪 Running the Project

1. Clone the repository
2. Open in Eclipse / IntelliJ
3. Run:

BankingAppApplication.java
4. Access Swagger:

http://localhost:8080/swagger-ui/index.html

---

## 🔮 Future Improvements

- Convert to **Microservices Architecture**
- Add **JWT Authentication**
- Integrate **MySQL/PostgreSQL**
- Add **Kafka (event-driven system)**
- Add **Angular frontend UI**

---

## 👨‍💻 Author

**Subham Mahapatro**

---
