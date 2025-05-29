# Java-Project-POO

### 🛒 Project Overview  
Developed a desktop application using **Java** and **Object-Oriented Programming (OOP)** principles in **IntelliJ IDEA** to manage operations in a Grocery Store. The system allows employees to register, edit, and view data related to clients, products, and sales through an organized and intuitive interface. Products are grouped into categories, enabling clients and staff to browse a structured product catalog. Each sale may optionally be linked to a specific client and automatically generates an invoice with tax calculations.

The platform supports a complete sales workflow, including stock management, employee authentication, and customer billing. Employees must authenticate using an ID and security code before performing administrative actions such as processing sales or updating inventory.

### 🔧 Key Features  
- Sale registration and real-time stock control  
- Customer and product management (add, edit, remove)  
- Invoice generation with VAT (IVA) breakdown  
- Detailed sale receipts and invoice listings  
- Statistical reports such as:  
  - Most/least sold products  
  - Top clients by purchase volume  
  - Revenue per day, week, or month  

### 💡 System Design  
The system was designed with clarity, security, and maintainability in mind. It includes exception handling and user feedback mechanisms to ensure a smooth experience. The interface layout was structured using intuitive menus and tables, making it easy to navigate through the store's functionalities.

Implementation was done in **Java** using **IntelliJ IDEA**, applying **OOP** concepts with a modular class structure comprising core components such as:

- **Loja**: Main class that manages sales, products, employees, and clients  
- **Venda**: Represents a completed sale with associated items and client  
- **Produto**: Contains product information, including stock and availability  
- **Funcionario**: Handles employee data and authentication  
- **Cliente**: Stores client information (e.g., name and NIF)  
- **Fatura**: Manages invoice generation and tax calculations  
- **ProdutoQuantidade**: Associates a product with a quantity during a sale  

Each class encapsulates its own logic and interacts with others to ensure system integrity and consistency across operations.

### ✅ Testing  
Comprehensive tests were performed to validate business rules and ensure all features function correctly under normal and edge-case conditions.

### 📸 Result

Below are some screenshots of the application, showcasing the main features and user interfaces:

#### 🏠 Welcome Page / Main Menu

![Welcome Page](https://github.com/user-attachments/assets/1038cbbf-9c92-446e-994c-4278591de38d)

![Main Menu](https://github.com/user-attachments/assets/e2995d23-1518-4e4d-b8dd-62fe74ecab34)

#### 👥 Client Registration
![Client Registration](https://github.com/user-attachments/assets/f31785f6-7b4b-41b8-be68-97bc9093344b)

#### 🛒 Sale with Invoice
![Sale with Invoice](https://github.com/user-attachments/assets/66678d02-9e33-43e6-a243-f8712bd437d3)

#### ⚙️ Administration Menu
![Admin Menu](https://github.com/user-attachments/assets/237c3214-579d-44f1-a667-f3f886eea3eb)

