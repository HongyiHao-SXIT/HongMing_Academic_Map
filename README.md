# 🔍 HM Map - Academic Search Platform

A full-stack academic resource management and search system built with **Spring Boot 3.5.3** and **Vue.js**. It supports user registration, paper upload, search, and usage statistics.

---

## 🧩 Features

- User registration / login
- User profile management
- Academic paper metadata upload
- Full paper file upload (local storage or object storage ready)
- Keyword-based fuzzy search and category filtering
- Download and citation statistics

---

## 🛠 Tech Stack

| Layer         | Technology                    |
|---------------|-------------------------------|
| Frontend      | Vue 3 + Axios + ElementPlus   |
| Backend       | Spring Boot 3.5.3 + Spring Security + JPA |
| Database      | MySQL 8.x                     |
| Build Tool    | Maven                         |
| Java Version  | Java 21                       |
| File Upload   | Multipart / MinIO (optional)  |

---

## 📁 Project Structure (Backend)

```

hm\_map/
├── src/
│   ├── main/
│   │   ├── java/com/hm\_map/hm\_map/
│   │   │   ├── controller/       // Controller layer
│   │   │   ├── entity/           // Entity classes
│   │   │   ├── repository/       // Data access layer
│   │   │   ├── service/          // Service logic layer (to be added)
│   │   │   └── HmMapApplication.java  // Main class
│   │   └── resources/
│   │       ├── application.properties // Config file
│   │       └── static/, templates/    // Currently unused
└── pom.xml

````

---

## ⚙️ Environment Setup

### 1. Create the Database

Use MySQL to create the required database:

```sql
CREATE DATABASE scholar_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
````

Then configure it in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/scholar_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/your-repo/hm_map.git
cd hm_map
```

2. Open the project in an IDE (e.g. IntelliJ IDEA) as a Maven project

3. Run the main application class:

```bash
com.hm_map.hm_map.HmMapApplication
```

4. API Test Examples (use Postman or similar tool):

* **GET all users:** `http://localhost:8080/api/test/users`
* **POST add a new user:**

```json
POST http://localhost:8080/api/test/add
{
  "name": "Alice",
  "account": "alice123",
  "password": "secret"
}
```

---

## ✅ Feature Roadmap

* [x] User and Paper entity models
* [x] Basic CRUD APIs
* [ ] Login authentication (JWT)
* [ ] Paper file uploads
* [ ] Full-text search (Elasticsearch optional)
* [ ] Vue frontend UI
* [ ] Admin panel and permission control

---

## 📌 Author

Hongyi Hao

---

## 📜 License

MIT License - Free to use, modify, and distribute

```

