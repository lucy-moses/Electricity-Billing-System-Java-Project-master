# Electricity Billing System ⚡

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Swing](https://img.shields.io/badge/Java%20Swing-GUI-yellowgreen)

A desktop application for managing electricity customer accounts, billing, and payments built with Java Swing and MySQL.

## Features ✨

- **User Authentication**
  - Admin and Customer login portals
  - Secure password handling
  - Role-based access control

- **Customer Management**
  - New customer registration
  - Meter number assignment
  - Customer information storage

- **Billing System**
  - Monthly bill generation
  - Consumption tracking
  - Bill status monitoring (Paid/Pending)

- **Payment Processing**
  - Payment recording
  - Receipt generation
  - Payment history

- **Admin Dashboard**
  - View all customers
  - Generate reports
  - Manage system settings

# Project Structure 📂
src/
├── electricity/

│   ├── billing/

│   │   ├── system/

│   │   │   ├── Database.java

│   │   │   ├── Login.java

│   │   │   ├── Signup.java

│   │   │   ├── main_class.java

│   │   │   └── ... (other classes)
resources/

├── icon/ (application icons)
database_schema.sql (complete DB schema)


# Video output:

https://1drv.ms/v/c/11952a2190070749/EZqERDDBcANBoO1nfbsI1zABqnp07EWER9AkBLzCJw1vXA


The admin can create a new customer with new meter information assigned to the customer, view all customer details, and calculate the bills of all the customers.

The user can create his own account after the admin has created his meter information and once the account is created user can view his account information.

It lets User perform multiple operations like:-

1- User can Signup and create account to track all bill details.

2- User can Calculate and pay their Electricity Bill .

3- User can update their own personal details.

4- User can Generate Bill.

5- User can see their meter info and the payment status of the bill.

About Project:
This Java application has been created using IntelliJ IDEA. Additional library was added for the support of JDBC (Required to setup the connection between the Database and Java Application).

It contains multiple numbers of different classes which works together to create a better user experience .

->Splash Screen class

->Signup Screen class

->Login Screen class

->Main class

->New Customer class

->Pay Bill class

->Generate Bill class

->Customer Details class

->Calculate Bill class

->Bill details class

->Deposit details class

->Meter info class

->Update information class

->View information class

->Database class(JDBC - MySQL)

Database (MySQL)
Database for this Electricity Billing System contains 5 Tables

->Signup Table (UserName,Password)

->New Customer Table(Name, MeterNumber, Address, State, City, Email, Phone)

->Meter Info Table(Meter Number, Meter Location, Meter Type, Phase Code, Bill Type, Days)

->Tax Table(Cost per unit, Meter Rent, Service Charge, Service Tax, Swacch Bharat Tax, Fixed Tax)

->Bill Table (Meter Number, Month, Unit, Total bill, status)

Java communicates with MySQL tables using JDBC which stands for Java Database Connectivity.


# Screenshots:
Customer Side:
Signup
Capture


![image](https://github.com/user-attachments/assets/54e0198a-3703-4f83-87f9-d23a80396fbe)


Login Page
Capture1


![image](https://github.com/user-attachments/assets/7e586e74-14be-4add-9a6d-ff987edd7a45)


Main Page
Capture2


![image](https://github.com/user-attachments/assets/365eb28a-8cb2-4046-a45e-101cfad5bc42)


Generate Bill
Capture3


![image](https://github.com/user-attachments/assets/8410fe35-94a6-4d77-a1b1-baf249cff6a0)



Pay Bill
Capture4


![image](https://github.com/user-attachments/assets/ac8049b6-6ae1-4314-9892-7c14b4315603)



Bill Details
6


![image](https://github.com/user-attachments/assets/aab1592a-17e2-444f-bd99-4f6f6cd1e99d)


Update Customer Information
7


![image](https://github.com/user-attachments/assets/7f28cbdb-7d0c-4ae1-92ab-872ecd9c0db4)



View Customer Information
8


![image](https://github.com/user-attachments/assets/64355045-03ad-4379-9640-5b578490e0c7)


# Admin Side:
Add New Customer
9


![image](https://github.com/user-attachments/assets/0f13d866-7f70-45a1-931f-9557d6605734)




Customer Details
10

![image](https://github.com/user-attachments/assets/ff96c79c-4ce0-496c-8b6b-3b99b28690a7)




Calculate Electricity Bill


![image](https://github.com/user-attachments/assets/6bdfa41f-0508-4a27-9710-986596bab025)
