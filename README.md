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

Login Page
Capture1

Main Page
Capture2

Generate Bill
Capture3

Pay Bill
Capture4

Payment Page Of Paytm
5

Bill Details
6

Update Customer Information
7

View Customer Information
8

Admin Side:
Add New Customer
9

Customer Details
10

Calculate Electricity Bill
