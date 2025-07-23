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

**## 🛠️ Create AWS Resources via Console**

### 🔐 1. Create a KMS Key

1. Go to **AWS Console → KMS (Key Management Service)**.
2. Click **“Create key”**.
3. Choose:
   - **Key type**: Symmetric  
   - **Key usage**: Encrypt and decrypt
4. Give it a name like `secure-notes-key`.
5. Enable access for your IAM user or role (e.g. your Beanstalk EC2 role later).
6. Finish and **note down the Key ID**.

---

### 📊 2. Create a DynamoDB Table

1. Go to **AWS Console → DynamoDB**.
2. Click **“Create table”**.
3. Set:
   - **Table name**: `SecureNotes`  
   - **Partition key**: `id` (String)
4. Leave the rest as default.
5. Click **Create Table**.

**## 🚀 Deploy to AWS Elastic Beanstalk**

### ✅ Step 1: Build your app locally

mvn clean package

This creates a .jar file in your target/ directory — this is the file you will upload.

🌐 Step 2: Log in to AWS Management Console
Go to https://console.aws.amazon.com/

Navigate to Elastic Beanstalk service.

📦 Step 3: Create a new Elastic Beanstalk Application
Click Create Application.

Enter an application name (e.g., MyJavaApp).

Optionally add a description.

Click Next.

🖥️ Step 4: Create a new Environment
Choose Web server environment.

Click Select.

⚙️ Step 5: Configure the environment
Environment name: e.g., myjava-env

Platform: Choose Java (e.g., "Corretto 21")

Application code: Select Upload your code

Upload the .jar file from your local target/ folder.

Click Next.

🔧 Step 6: Configure more options (optional)
You can configure things like instance type, capacity, scaling, environment variables, database, etc.

For a simple deploy, you can skip this and click Next.

✅ Step 7: Review and Create Environment
Review your settings.

Click Create environment.

AWS will provision EC2, Load Balancer, Security Groups, etc., and deploy your app. This might take a few minutes.

🌍 Step 8: Access your deployed app
Once the environment status is Green and health is Ok, you’ll see a URL like:
http://myjava-env.eba-xyz123.us-east-1.elasticbeanstalk.com/
Click it or open it in your browser.

Your Java app should be running now! (Don't forget to add 'notes/' to the URL to avoid Not found Error)

Happy coding !
