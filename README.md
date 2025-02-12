# 📚 E-Library System

![Java](https://img.shields.io/badge/Java-17-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue) ![MIT License](https://img.shields.io/badge/License-MIT-brightgreen)

A modern **E-Library System** built with **Spring Boot** & **PostgreSQL**, offering user authentication, book management, and issue tracking.

## 🚀 Features

✅ JWT-based authentication  
✅ Role-based access control (Admin & Member)  
✅ CRUD operations for books & members  
✅ Redis caching for better performance  


## 🛠️ Installation

```sh
git clone https://github.com/yourusername/e-library.git
cd e-library
mvn clean install
mvn spring-boot:run
```

## 🔥 API Endpoints

### 📚 Get All Books
```http
GET /api/books
```

### ➕ Add a Book
```http
POST /api/books
```
**Body:**
```json
{
  "title": "Spring Boot Guide",
  "author": "John Doe",
  "isbn": "1234567890"
}
```

## 🤝 Contribution Guidelines

1. 🍴 Fork the project  
2. Create a new branch (`feature-xyz`)  
3. Commit changes (`git commit -m "Added new feature"`)  
4. Push to your branch (`git push origin feature-xyz`)  
5. 🚀 Create a Pull Request  

## 📜 License

This project is **MIT Licensed**.

---

