# 📧 MailSender Service

MailSender is a Spring Boot microservice for sending and verifying one-time verification codes via email. It uses secure code generation and a time-limited cache to store codes safely. Ideal for user registration, two-factor authentication (2FA), or password recovery.

---

## 🚀 Features

### Send Verification Code
Generates a 6-digit secure code using `SecureRandom` and sends it to the specified email.

### Check Code
Verifies the code against the cached value. Codes are invalidated immediately after successful verification.

### Time-to-Live (TTL) for Codes
Codes are stored in a cache with a configurable lifetime and automatically expire.

### Thread-Safe & Reliable
Uses `SecureRandom` for secure code generation and `Caffeine Cache` for fast, in-memory storage.

### Input Validation
All requests are validated using `@Valid` annotations, including email format and code presence.

---

## 📦 Technologies

- Java 21  
- Spring Boot 3+  
- Spring Mail (`JavaMailSender`)  
- Caffeine Cache  
- `spring-boot-starter-validation`

---

## 🔄 CI/CD Pipeline with GitHub Actions
The project includes an automated CI/CD pipeline that builds and deploys the service using GitHub Actions and Docker.

### Pipeline Stages:
1. Build Stage
2. Deploy Stage

[![Build Status](https://github.com/phyphloran/mailsender-service/actions/workflows/workflow.yml/badge.svg)](https://github.com/phyphloran/mailsender-service/actions/workflows/workflow.yml)

---

## 📸 Frontend Screenshots

<div align="center">

| Feature | Screenshot | Description |
|---------|------------|-------------|
| **🏠 Main Page** | <img src="https://github.com/phyphloran/mailsender-service/blob/main/pics/main-page.png" alt="Main Page" width="400" style="border-radius: 8px; border: 1px solid #ddd; box-shadow: 0 4px 8px rgba(0,0,0,0.1);"> | **Main Interface**<br>Enter email to request verification codes for registration, 2FA, or password recovery |
| **✅ Successful Verification** | <img src="https://github.com/phyphloran/mailsender-service/blob/main/pics/success-verified.png" alt="Successful Verification" width="400" style="border-radius: 8px; border: 1px solid #ddd; box-shadow: 0 4px 8px rgba(0,0,0,0.1);"> | **Validation Success**<br>Confirmation screen displayed when the verification code is successfully validated |
| **❌ Invalid/Expired Code** | <img src="https://github.com/phyphloran/mailsender-service/blob/main/pics/incorrect-code.png" alt="Invalid Code" width="400" style="border-radius: 8px; border: 1px solid #ddd; box-shadow: 0 4px 8px rgba(0,0,0,0.1);"> | **Error State**<br>Error message shown when code is incorrect, invalid, or has expired |

</div>
