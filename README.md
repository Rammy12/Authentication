# Authentication

A simple authentication system built using Java and Spring Boot, implementing user registration, login, session management, and token-based authentication.

## 🚀 Features
- User Registration
- User Login with JWT Authentication
- Role-Based Access Control (RBAC)
- Password Hashing with BCrypt
- Session Management
- Secure API Endpoints
- Spring Security Integration

## 🛠 Technologies Used
- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Hibernate
- Lombok
- Maven
- MySQL/PostgreSQL (Database)

## 📂 Project Structure
```
Authentication/
│-- src/main/java/com/example/authentication
│   │-- controller/  # API Controllers
│   │-- service/     # Business logic layer
│   │-- repository/  # Data access layer
│   │-- model/       # User entity
│   │-- security/    # JWT and security config
│   └-- AuthenticationApplication.java # Main application
│-- src/main/resources/
│   └-- application.properties  # Configuration file
│-- pom.xml  # Maven dependencies
```

## ⚙️ Setup and Installation
### Prerequisites
- Java 17+
- Maven
- MySQL

### Steps to Run the Project
1. **Clone the repository:**
   ```sh
   git clone https://github.com/Rammy12/Authentication.git
   cd Authentication
   ```

2. **Configure Database:**
   Update `src/main/resources/application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build and Run the Project:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access API Endpoints:**
   - Register User: `POST /auth/register`
   - Login User: `POST /auth/login`
   - Get User Info (Authenticated): `GET /users/me`

## 🤝 Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## 📧 Contact
For any questions, contact **[Ramesh Kumar]** at `rameshkumar455555@gmail.com`.

