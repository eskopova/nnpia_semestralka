# TopPecivo - Pastery Reviews

## Introduction

Welcome to **TopPecivo**, your go-to platform for honest and comprehensive reviews of various pastery products. This project is divided into two main parts: the backend, which handles user management, database of stores, products, and user reviews; and the frontend, which presents the data to users in an intuitive and user-friendly manner.

### Technologies Used
- **Frontend:** React, Axios
- **Backend:** Spring Boot, Spring Security, Spring Data JPA, JWT
- **Database:** MySQL

## Backend

The backend of TopPecivo is built using Java Spring Boot. It provides an infrastructure for managing users, stores, products, and reviews.

### Schema Visualisation
![alt text](https://raw.githubusercontent.com/eskopova/nnpia_semestralka/main/diagram.png)

### Key Features
- **User Management**: Handles user registration, authentication, and authentization.
- **Store Database**: Maintains a comprehensive list of stores where different products can be purchased.
- **Product Database**: Keeps track of various pastery products, their details, and attributes, computes its rating from reviews and supports paging and sorting.
- **Review System**: Allows users to post reviews, rate products, and view reviews from other users.

## Frontend

The frontend of TopPecivo is a web application that utilizes the backend services to provide users with an engaging and informative experience. It allows users to browse through different pastery products, read reviews, and share their own experiences. Users are able to browse all products, products by store or products by category.

### Key Features
- **Product Browsing**: Users can search and browse through a variety of pastery products.
- **Review Display**: Displays reviews and ratings from other users.
- **User Interaction**: Registered users can post their reviews and rate products.

## Getting Started

To get started with TopPecivo, follow these steps:

1. **Clone the repository**

2. **Backend Setup**:
    - Ensure Docker is installed and running, then start the MySQL container with docker-compose
    - Build, connect to db and run the Spring Boot application

3. **Frontend Setup**:
    - Navigate to the frontend directory
    - Install dependencies and start the development server:
        ```sh
        npm install
        npm start
        ```

4. **Access the Application**:
    - Open your web browser and go to `http://localhost:3000` to see the frontend in action.
    - The backend API should be accessible at `http://localhost:9000`.
