# 🛡️ Secure Note App

A secure note-taking web application built with **Spring Boot** and deployed on **AWS Elastic Beanstalk**. It encrypts notes using **AWS KMS (Key Management Service)** and stores them securely in **Amazon DynamoDB**.

---

## 🔐 Features

- Create and retrieve encrypted notes
- Encryption using AWS KMS
- No plaintext content is stored
- RESTful API with basic endpoints
- Easy deployment on AWS Elastic Beanstalk

---

## 🧰 Tech Stack

- Java 21
- Spring Boot 3.5.3
- AWS SDK for Java v2
- AWS KMS
- Amazon DynamoDB
- Maven

---

## 🚀 How It Works

1. A user sends a note via the REST API.
2. The note content is encrypted using AWS KMS.
3. The encrypted note is stored in DynamoDB.
4. When retrieved, the note is decrypted and returned.

---

## 📦 API Endpoints

| Method | Endpoint     | Description             |
|--------|--------------|-------------------------|
| `POST` | `/notes`     | Create a new note       |
| `GET`  | `/notes/{id}`| Get and decrypt a note  |

Example JSON:
```json
{
  "id": "note123",
  "content": "This is a secure note"
}
