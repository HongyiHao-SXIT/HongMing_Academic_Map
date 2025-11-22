# 📚 HongMing Academic Map

A full-stack academic paper management platform that supports secure user registration, paper upload and retrieval, citation tracking, advanced search, and administrative review.

---

## 🔧 Tech Stack

### Backend

* Spring Boot 3.5 (Java 21)
* Spring Security + JWT
* Spring Data JPA (Hibernate)
* MySQL
* Lombok

### Frontend

* Vue 3 + Vite
* Element Plus
* Pinia (Vuex Alternative)
* Axios
* ESLint + Prettier

---

## 📁 Project Structure

### Backend `hmmapback/`

```
src/
└── com/hm_map/hm_map/
    ├── controller/
    │   ├── AuthController.java
    │   ├── UserController.java
    │   ├── PaperController.java
    │   └── AdminController.java
    ├── entity/
    │   ├── User.java
    │   └── Paper.java
    ├── repository/
    │   ├── UserRepository.java
    │   └── PaperRepository.java
    ├── service/
    │   ├── UserService.java
    │   └── PaperService.java
    ├── service/impl/
    │   ├── UserServiceImpl.java
    │   └── PaperServiceImpl.java
    └── security/
        ├── JwtAuthenticationFilter.java
        ├── JwtTokenUtil.java
        ├── SecurityConfig.java
        └── UserDetailsServiceImpl.java
```

### Frontend `hmmapfront/`

```
src/
├── components/
│   ├── Admin/
│   │   ├── UserTable.vue
│   │   └── PaperReviewTable.vue
│   ├── Paper/
│   │   ├── PaperCard.vue
│   │   ├── PaperList.vue
│   │   └── UploadPaper.vue
│   ├── User/
│   │   ├── LoginForm.vue
│   │   ├── RegisterForm.vue
│   │   └── ProfileForm.vue
│   └── Common/
│       ├── SearchBar.vue
│       ├── TagList.vue
│       ├── Pagination.vue
│       └── LoadingSpinner.vue
├── views/
│   ├── HomePage.vue
│   ├── LoginPage.vue
│   ├── RegisterPage.vue
│   ├── RegisterPage.vue
│   ├── UploadPage.vue
│   ├── PaperDetail.vue
│   └── AdminPanel.vue
├── store/
│   └── auth.js (Pinia)
├── router/
│   └── index.js
├── App.vue
└── main.js
```

---

## 🌟 Features

### General

* JWT-based authentication
* Secure password hashing and storage
* Responsive UI with Element Plus
* File upload (PDF)
* Global error handling and loading indicators

### User

* Register / login / logout
* Edit profile and change password
* Upload papers
* Browse, search, filter papers
* View/download paper with statistics
* Tagging and citation management

### Admin

* View all users and papers
* Delete or modify any user or paper
* Moderate paper visibility
* Manage tags and citation count
* Role-based access control (USER / ADMIN)

---

## 🗄️ Database Schema (MySQL)

```sql
CREATE DATABASE hmmap CHARACTER SET utf8mb4;

USE hmmap;

CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  role VARCHAR(20) DEFAULT 'USER',
  create_time DATETIME,
  update_time DATETIME
);

CREATE TABLE paper (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  abstract_text TEXT,
  authors VARCHAR(255),
  affiliation VARCHAR(255),
  keywords TEXT,
  publish_date DATE,
  doi VARCHAR(100),
  publication VARCHAR(100),
  type VARCHAR(50),
  categories VARCHAR(100),
  file_path VARCHAR(255),
  uploader_id BIGINT,
  is_public BOOLEAN DEFAULT TRUE,
  is_deleted BOOLEAN DEFAULT FALSE,
  view_count INT DEFAULT 0,
  download_count INT DEFAULT 0,
  citation_count INT DEFAULT 0,
  tags TEXT,
  create_time DATETIME,
  update_time DATETIME
);
```

---

## ⚙️ Configuration

### `application.yml` (Backend)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hmmap?useSSL=false&serverTimezone=UTC
    username: root
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect

jwt:
  secret: my-secret-key
  expiration: 86400000
```

---

## 🚀 Getting Started

### Backend

```bash
cd hmmapback
./mvnw spring-boot:run
```

### Frontend

```bash
cd hmmapfront
npm install
npm run dev
```

---

## 🔐 Authentication

All protected endpoints require a JWT token.

Include the token in the request header:

```
Authorization: Bearer <your_token>
```

---

## 📂 API Overview

| Method | Endpoint                          | Description                 |
| ------ | --------------------------------- | --------------------------- |
| POST   | `/api/auth/register`              | Register user               |
| POST   | `/api/auth/login`                 | Authenticate and return JWT |
| GET    | `/api/users/profile`              | Get current user info       |
| PUT    | `/api/users/{id}/password`        | Change password             |
| GET    | `/api/papers/list?page=0&size=10` | Get paginated papers        |
| GET    | `/api/papers/search?title=abc`    | Search by title             |
| POST   | `/api/papers/upload`              | Upload a paper              |
| GET    | `/api/papers/{id}`                | Get paper by ID             |
| GET    | `/api/papers/download/{id}`       | Download paper              |
| PUT    | `/api/papers/{id}`                | Update paper                |
| DELETE | `/api/papers/{id}`                | Delete paper (admin only)   |

---

## 📦 Build & Deploy

### Backend

```bash
./mvnw clean package
java -jar target/hmmapback-0.0.1-SNAPSHOT.jar
```

### Frontend

```bash
npm run build
```

Then serve the `dist/` folder with Nginx or embed it in Spring Boot.

---

## 🧪 Demo Credentials

| Role  | Username   | Password   |
| ----- | ---------- | ---------- |
| Admin | `admin`    | `admin123` |
| User  | `testuser` | `123456`   |

---

## 📌 Roadmap

* [ ] ElasticSearch integration for full-text search
* [ ] Online PDF preview
* [ ] Paper recommendation system
* [ ] Import/export via Excel
* [ ] Notifications and audit logs

---

## 📃 License

MIT License © 2025 HongMIng Intelligent Technology

---