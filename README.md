# Food Delivery App

## Overview

The Food Delivery App is a Java-based application designed to simulate the operations of a food delivery service. It allows users to register, log in, browse restaurants, place orders, and manage their accounts. The application also supports restaurant management, rider assignments, and voucher usage.

## Features

- **User Management**: Register, log in, and manage user accounts.
- **Restaurant Management**: Add, update, and view restaurant details, including menus and reviews.
- **Order Management**: Create, view, and manage orders with real-time status updates.
- **Voucher System**: Apply vouchers for discounts on orders.
- **Rider Management**: Assign riders to orders and track deliveries.
- **Database Integration**: Persistent storage using MySQL.

## Project Structure

The project is organized into the following modules:

- **Modules**:
  - `User.java`, `Rider.java`, `Restaurant.java`, `Produs.java`, `Voucher.java`, `Recenzie.java`, `Comanda.java`
- **Services**:
  - `UserService.java`, `LoginService.java`
- **Utils**:
  - `Database.java`
- **Menu**:
  - `Menu.java`, `Main.java`

## Key Classes

### `User`

Represents a user in the system with attributes like name, email, and a list of orders.

### `Restaurant`

Manages restaurant details, including menu items and reviews.

### `Comanda`

Handles order details, including the user, restaurant, and list of products.

### `Voucher`

Implements a discount system with attributes like code, value, and expiration date.

### `Database`

Provides database connectivity and initializes required tables.

## Setup Instructions

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL Connector for Java (included in `lib/` folder)

### Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```bash
   cd Food-Delivery-App
   ```
3. Compile the project:
   ```bash
   javac -cp lib/mysql-connector-j-8.4.0.jar src/*.java
   ```
4. Run the application:
   ```bash
   java -cp .:lib/mysql-connector-j-8.4.0.jar src.Main
   ```

## Usage

1. Start the application.
2. Register or log in as a user.
3. Browse restaurants and their menus.
4. Place an order and apply a voucher if available.
5. Track the status of your order.

## Future Improvements

- Add a graphical user interface (GUI).
- Implement advanced search and filtering for restaurants and products.
- Enhance security features, such as password encryption.
- Add support for multiple languages.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
