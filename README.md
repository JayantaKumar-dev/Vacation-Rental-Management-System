# 🏡 HMSApp – Vacation Rental Management System

**HMSApp** is a full-stack Vacation Rental Management System inspired by Airbnb, built using **Spring Boot (Backend)** and **React + Tailwind CSS (Frontend)**. It enables property listing, booking, and user role-based access for hosts and guests.

> 💡 Built as a capstone project at **Pankaj Sir’s Full-Stack Java Academy**

![Java](https://img.shields.io/badge/Java-17+-red.svg)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green.svg)
![React](https://img.shields.io/badge/Frontend-React-blue)
![Tailwind CSS](https://img.shields.io/badge/Styling-TailwindCSS-teal)
![JWT](https://img.shields.io/badge/Security-JWT-orange)
![Swagger](https://img.shields.io/badge/API-Swagger-brightgreen)

---

## 🚀 Features

- 🧾 **User Registration & Login** (Guests & Hosts)
- 🏘️ **Host Dashboard**: Add, update, and delete property listings
- 🌍 **Property Search**: Filter by city and country
- 📆 **Booking System**: Guests can book available properties (to be added)
- 🔐 **Role-Based Access Control** via JWT tokens
- 📄 **API Documentation** with Swagger UI
- 📦 **Clean MVC architecture** with layered service and DTO pattern

---

## 🧰 Tech Stack

| Layer       | Technology                          |
|------------|--------------------------------------|
| Backend     | Java 17, Spring Boot 3.x, Spring Security |
| Frontend    | React.js, Tailwind CSS              |
| Auth        | JWT (JSON Web Tokens)               |
| Database    | MySQL                               |
| API Docs    | Swagger (Springdoc OpenAPI)         |

---

## 📸 Swagger UI (Backend API Docs)

After starting the backend, access Swagger at:

**[`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)**

---

## 📂 Project Structure

### Backend (Spring Boot)


---

## 🔐 Security Overview

- **Spring Security + JWT** for stateless authentication
- **Roles** embedded in JWT token
- Endpoints protected via `@PreAuthorize` or `HttpSecurity` config
- Passwords are **hashed using BCrypt**

---

## 🎯 Key Endpoints

| Method | Endpoint                | Description                       | Auth Required |
|--------|-------------------------|-----------------------------------|---------------|
| POST   | `/auth/signup`          | Register a new user (Guest/Host)  | ❌ No         |
| POST   | `/auth/login`           | Authenticate user and return JWT  | ❌ No         |
| GET    | `/profile`              | Fetch current user's profile      | ✅ Yes        |
| POST   | `/properties`           | Add property (Host only)          | ✅ Yes        |
| PUT    | `/properties/{id}`      | Update property                   | ✅ Yes        |
| DELETE | `/properties/{id}`      | Delete property                   | ✅ Yes        |
| GET    | `/properties`           | List all or search properties     | ❌ No         |

---

## ⚙️ Getting Started

### ✅ Prerequisites

- Java 17+
- Maven
- MySQL

# Clone the repo
git clone https://github.com/your-username/hmsapp-backend.git
cd hmsapp-backend

# Update database credentials in application.properties

# Build & run the application
./mvnw spring-boot:run

---

💡 Future Enhancements
🗓️ Booking System

💳 Payment Integration (e.g., Stripe)

📷 Image Uploads via Cloudinary

🛡️ Admin Dashboard for user/listing management

📊 Analytics for hosts

----------------------------------------------------------------------
📫 Author
Built with ❤️ by Jayant Samal

----------------------------------------------------------------------
Happy to polish it further based on your style!
